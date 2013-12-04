import scalariform.formatter.preferences._

scalariformSettings ++ Seq(
    ScalariformKeys.preferences := FormattingPreferences().
        setPreference(AlignParameters, true).
        setPreference(IndentSpaces, 4).
        setPreference(AlignSingleLineCaseStatements, true).
        setPreference(PreserveDanglingCloseParenthesis, true).
        setPreference(PreserveSpaceBeforeArguments, true)
)
