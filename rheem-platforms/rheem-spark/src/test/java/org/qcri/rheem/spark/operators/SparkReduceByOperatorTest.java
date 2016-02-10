package org.qcri.rheem.spark.operators;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaRDDLike;
import org.junit.Assert;
import org.junit.Test;
import org.qcri.rheem.basic.data.Tuple2;
import org.qcri.rheem.basic.function.ProjectionDescriptor;
import org.qcri.rheem.core.function.ReduceDescriptor;
import org.qcri.rheem.core.types.DataSetType;
import org.qcri.rheem.core.types.DataUnitType;
import org.qcri.rheem.spark.compiler.FunctionCompiler;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Test suite for {@link SparkReduceByOperator}.
 */
public class SparkReduceByOperatorTest extends SparkOperatorTestBase {

    @Test
    public void testExecution() {
        // Prepare test data.
        List<Tuple2<String, Integer>> inputList = Arrays.stream("aaabbccccdeefff".split(""))
                .map(string -> new Tuple2<>(string, 1))
                .collect(Collectors.toList());
        final JavaRDD<Tuple2<String, Integer>> inputRdd = this.getSC().parallelize(inputList);

        // Build the reduce operator.
        SparkReduceByOperator<Tuple2<String, Integer>, String> reduceByOperator =
                new SparkReduceByOperator<>(
                        DataSetType.createDefaultUnchecked(Tuple2.class),
                        new ProjectionDescriptor<>(
                                DataUnitType.createBasicUnchecked(Tuple2.class),
                                DataUnitType.createBasic(String.class),
                                "field0"),
                        new ReduceDescriptor<>(
                                DataUnitType.createGroupedUnchecked(Tuple2.class),
                                DataUnitType.createBasicUnchecked(Tuple2.class),
                                (a, b) -> {
                                    a.field1 += b.field1;
                                    return a;
                                }));

        // Execute the reduce operator.
        final JavaRDDLike[] outputRdds = reduceByOperator.evaluate(new JavaRDDLike[]{inputRdd}, new FunctionCompiler(), this.sparkExecutor);

        // Verify the outcome.
        Assert.assertEquals(1, outputRdds.length);
        final Iterable<Tuple2<String, Integer>> result = outputRdds[0].collect();
        final Set<Tuple2<String, Integer>> resultSet = new HashSet<>();
        result.forEach(resultSet::add);
        final Tuple2[] expectedResults = {
                new Tuple2<>("a", 3),
                new Tuple2<>("b", 2),
                new Tuple2<>("c", 4),
                new Tuple2<>("d", 1),
                new Tuple2<>("e", 2),
                new Tuple2<>("f", 3)
        };
        Arrays.stream(expectedResults)
                .forEach(expected -> Assert.assertTrue("Not contained: " + expected, resultSet.contains(expected)));
        Assert.assertEquals(expectedResults.length, resultSet.size());

    }
}