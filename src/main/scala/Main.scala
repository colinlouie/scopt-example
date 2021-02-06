/** This example micro-application targets CLI parsing, as is part of a series to work towards a larger application. */

/** CliOpts is used to store the parsed args, and options -values into.
  *
  * Best practices: This case class should be in its own folder/file, and namespace.
  *
  * e.g.
  * {{{
  *   src/main/scala/model/CliOpts.scala:
  *
  *     package <tld>.<domain>.<app name>.model.CliOpts
  *
  *     case class CliOpts(
  *       :
  *     )
  * }}}
  *
  * Note the use of Option does not dictate whether a command line option is required or optional. This is not the scope
  * of this data structure.
  *
  * @param inputParquet
  *   Path to the input Parquet.
  * @param outputParquet
  *   Path to the output Parquet.
  */
case class CliOpts(
  inputParquet: Option[String] = None,
  outputParquet: Option[String] = None
) // case class CliOpts

object Main {

  /** Application entrypoint.
    *
    * @param args
    *   args/options as-is from the operating system.
    */
  def main(args: Array[String]): Unit = {
    val cliOpts = getCliOpts(args)

    println(cliOpts)
  } // def main

  /** Place to shove CLI parsing under the rug, instead of having this all in main().
    *
    * @param args
    *   args/options as-is from the operating system.
    * @return
    *   a tidy idiomatic Scala data structure to operate against.
    */
  def getCliOpts(args: Array[String]): CliOpts = {
    val builder = scopt.OParser.builder[CliOpts]
    val parser = {
      import builder._
      scopt.OParser.sequence(
        opt[Option[String]]("input-parquet")
          .optional()
          .text("Results from previous job, to merge with this job.")
          .valueName("PATH")
          .action((value, config) => config.copy(inputParquet = value)),
        opt[Option[String]]("output-parquet")
          .required()
          .text("Path to write the results from this job.")
          .valueName("PATH")
          .action((value, config) => config.copy(outputParquet = value))
      )
    }

    val cliOpts = scopt.OParser.parse(parser, args, CliOpts()).get

    /** You will want to perform sanity checks here.
      *
      * For example, scopt does not have a mutually-exclusive check. That is, you cannot define for option X or Y to be
      * set, but not both.
      *
      * e.g.
      * {{{
      *   if (cliOpts.<X>.isDefined && cliOpts.<Y>.isDefined) {
      *     throw new <Custom>Exception("specify one of: --x or --y, but not both.")
      *   }
      * }}}
      */

    cliOpts
  } // get getCliOpts

} // object Main
