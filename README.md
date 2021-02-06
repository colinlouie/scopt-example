# Scopt example

This example micro-application demonstrates the bare minimum usage of the `scopt` library to obtain args/options from
the command line.

This is a part of a series of micro-applications that integrate into a larger application that utilizes Apache Spark.

This is the target execution:

`spark-submit target/scala-2.12/scopt-example-assembly-0.1.jar --output-parquet 123.parquet`

This has been tested with Spark 3.0.0 / Scala 2.12.10.

# Nomenclature

I use `args` to denote positional arguments, and `options` to denote items such as `-x` (which take no arguments) and 
`--x "hello world"` (which take a single argument). This may differ from the nomenclature `scopt` that uses. As I am 
only use options at this time, please familiarize yourself with `scopt`'s documentation. 

# Walk-through

## Running without args/options

Without any args/options, the **required** option(s) are enforced, then the _Usage_ is displayed.

Note: sbt-specific output has been suppressed from the following snippets.

```
sbt> run
Error: Missing option --output-parquet
Usage:  [options]

  --input-parquet PATH   Results from previous job, to merge with this job.
  --output-parquet PATH  Path to write the results from this job.
```

## Running with the required args/options

Using Scala case classes is beyond the scope of this document. This is what the `println` shows for `CliOpts` to 
give you an idea of how using scopt works.

Note: sbt-specific output has been suppressed from the following snippets.

```
sbt> run --output-parquet "s3://<my bucket>/123.parquet"
CliOpts(None,Some(s3://<my bucket>/123.parquet))
```

```
sbt> run --output-parquet "s3://<my bucket>/456.parquet" --input-parquet "s3://<my bucket>/123.parquet"
CliOpts(Some(s3://<my bucket>/123.parquet),Some(s3://<my bucket>/456.parquet))
```
