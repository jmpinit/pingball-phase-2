// Generated from Board.g4 by ANTLR 4.0

package boardfile;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BoardLexer extends Lexer {
    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache =
        new PredictionContextCache();
    public static final int
        BOARDW=1, EQ=2, NAMEL=3, GRAVITY=4, FRICTION1=5, FRICTION2=6, XV=7, YV=8, 
        X=9, Y=10, ORT=11, WIDTH=12, HEIGHT=13, TRIGGER=14, ACTION=15, BALL=16, 
        SQB=17, CCB=18, TRIB=19, LEFTF=20, RIGHTF=21, ABS=22, FIRE=23, INT=24, 
        FLOAT=25, NAME=26, WHITESPACE=27, COMMENT=28;
    public static String[] modeNames = {
        "DEFAULT_MODE"
    };

    public static final String[] tokenNames = {
        "<INVALID>",
        "'board'", "'='", "'name'", "'gravity'", "'friction1'", "'friction2'", 
        "'xVelocity'", "'yVelocity'", "'x'", "'y'", "'orientation'", "'width'", 
        "'height'", "'trigger'", "'action'", "'ball'", "'squareBumper'", "'circleBumper'", 
        "'triangleBumper'", "'leftFlipper'", "'rightFlipper'", "'absorber'", "'fire'", 
        "INT", "FLOAT", "NAME", "WHITESPACE", "COMMENT"
    };
    public static final String[] ruleNames = {
        "BOARDW", "EQ", "NAMEL", "GRAVITY", "FRICTION1", "FRICTION2", "XV", "YV", 
        "X", "Y", "ORT", "WIDTH", "HEIGHT", "TRIGGER", "ACTION", "BALL", "SQB", 
        "CCB", "TRIB", "LEFTF", "RIGHTF", "ABS", "FIRE", "INT", "FLOAT", "NAME", 
        "WHITESPACE", "COMMENT"
    };


    /**
     * Call this method to have the lexer or parser throw a RuntimeException if
     * it encounters an error.
     */
    public void reportErrorsAsExceptions() {
        addErrorListener(new ExceptionThrowingErrorListener());
    }

    private static class ExceptionThrowingErrorListener extends BaseErrorListener {
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer,
                Object offendingSymbol, int line, int charPositionInLine,
                String msg, RecognitionException e) {
            throw new RuntimeException(msg);
        }
    }


    public BoardLexer(CharStream input) {
        super(input);
        _interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
    }

    @Override
    public String getGrammarFileName() { return "Board.g4"; }

    @Override
    public String[] getTokenNames() { return tokenNames; }

    @Override
    public String[] getRuleNames() { return ruleNames; }

    @Override
    public String[] getModeNames() { return modeNames; }

    @Override
    public ATN getATN() { return _ATN; }

    @Override
    public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
        switch (ruleIndex) {
            case 26: WHITESPACE_action((RuleContext)_localctx, actionIndex); break;

            case 27: COMMENT_action((RuleContext)_localctx, actionIndex); break;
        }
    }
    private void WHITESPACE_action(RuleContext _localctx, int actionIndex) {
        switch (actionIndex) {
            case 0: skip();  break;
        }
    }
    private void COMMENT_action(RuleContext _localctx, int actionIndex) {
        switch (actionIndex) {
            case 1: skip();  break;
        }
    }

    public static final String _serializedATN =
        "\2\4\36\u0130\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b"+
        "\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20"+
        "\t\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27"+
        "\t\27\4\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\3\2"+
        "\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3"+
        "\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7"+
        "\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3"+
        "\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3"+
        "\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16"+
        "\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20"+
        "\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22"+
        "\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23"+
        "\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
        "\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25"+
        "\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
        "\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
        "\3\30\3\30\3\30\3\30\3\30\3\31\6\31\u00fb\n\31\r\31\16\31\u00fc\3\32\5"+
        "\32\u0100\n\32\3\32\6\32\u0103\n\32\r\32\16\32\u0104\3\32\3\32\7\32\u0109"+
        "\n\32\f\32\16\32\u010c\13\32\3\32\5\32\u010f\n\32\3\32\6\32\u0112\n\32"+
        "\r\32\16\32\u0113\5\32\u0116\n\32\3\33\5\33\u0119\n\33\3\33\7\33\u011c"+
        "\n\33\f\33\16\33\u011f\13\33\3\34\6\34\u0122\n\34\r\34\16\34\u0123\3\34"+
        "\3\34\3\35\3\35\7\35\u012a\n\35\f\35\16\35\u012d\13\35\3\35\3\35\2\36"+
        "\3\3\1\5\4\1\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27"+
        "\r\1\31\16\1\33\17\1\35\20\1\37\21\1!\22\1#\23\1%\24\1\'\25\1)\26\1+\27"+
        "\1-\30\1/\31\1\61\32\1\63\33\1\65\34\1\67\35\29\36\3\3\2\6\5C\\aac|\6"+
        "\62;C\\aac|\5\13\f\17\17\"\"\4\f\f\17\17\u0139\2\3\3\2\2\2\2\5\3\2\2\2"+
        "\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3"+
        "\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2"+
        "\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2"+
        "\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2"+
        "\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\3;\3\2\2\2\5A\3\2\2\2\7C\3\2"+
        "\2\2\tH\3\2\2\2\13P\3\2\2\2\rZ\3\2\2\2\17d\3\2\2\2\21n\3\2\2\2\23x\3\2"+
        "\2\2\25z\3\2\2\2\27|\3\2\2\2\31\u0088\3\2\2\2\33\u008e\3\2\2\2\35\u0095"+
        "\3\2\2\2\37\u009d\3\2\2\2!\u00a4\3\2\2\2#\u00a9\3\2\2\2%\u00b6\3\2\2\2"+
        "\'\u00c3\3\2\2\2)\u00d2\3\2\2\2+\u00de\3\2\2\2-\u00eb\3\2\2\2/\u00f4\3"+
        "\2\2\2\61\u00fa\3\2\2\2\63\u00ff\3\2\2\2\65\u0118\3\2\2\2\67\u0121\3\2"+
        "\2\29\u0127\3\2\2\2;<\7d\2\2<=\7q\2\2=>\7c\2\2>?\7t\2\2?@\7f\2\2@\4\3"+
        "\2\2\2AB\7?\2\2B\6\3\2\2\2CD\7p\2\2DE\7c\2\2EF\7o\2\2FG\7g\2\2G\b\3\2"+
        "\2\2HI\7i\2\2IJ\7t\2\2JK\7c\2\2KL\7x\2\2LM\7k\2\2MN\7v\2\2NO\7{\2\2O\n"+
        "\3\2\2\2PQ\7h\2\2QR\7t\2\2RS\7k\2\2ST\7e\2\2TU\7v\2\2UV\7k\2\2VW\7q\2"+
        "\2WX\7p\2\2XY\7\63\2\2Y\f\3\2\2\2Z[\7h\2\2[\\\7t\2\2\\]\7k\2\2]^\7e\2"+
        "\2^_\7v\2\2_`\7k\2\2`a\7q\2\2ab\7p\2\2bc\7\64\2\2c\16\3\2\2\2de\7z\2\2"+
        "ef\7X\2\2fg\7g\2\2gh\7n\2\2hi\7q\2\2ij\7e\2\2jk\7k\2\2kl\7v\2\2lm\7{\2"+
        "\2m\20\3\2\2\2no\7{\2\2op\7X\2\2pq\7g\2\2qr\7n\2\2rs\7q\2\2st\7e\2\2t"+
        "u\7k\2\2uv\7v\2\2vw\7{\2\2w\22\3\2\2\2xy\7z\2\2y\24\3\2\2\2z{\7{\2\2{"+
        "\26\3\2\2\2|}\7q\2\2}~\7t\2\2~\177\7k\2\2\177\u0080\7g\2\2\u0080\u0081"+
        "\7p\2\2\u0081\u0082\7v\2\2\u0082\u0083\7c\2\2\u0083\u0084\7v\2\2\u0084"+
        "\u0085\7k\2\2\u0085\u0086\7q\2\2\u0086\u0087\7p\2\2\u0087\30\3\2\2\2\u0088"+
        "\u0089\7y\2\2\u0089\u008a\7k\2\2\u008a\u008b\7f\2\2\u008b\u008c\7v\2\2"+
        "\u008c\u008d\7j\2\2\u008d\32\3\2\2\2\u008e\u008f\7j\2\2\u008f\u0090\7"+
        "g\2\2\u0090\u0091\7k\2\2\u0091\u0092\7i\2\2\u0092\u0093\7j\2\2\u0093\u0094"+
        "\7v\2\2\u0094\34\3\2\2\2\u0095\u0096\7v\2\2\u0096\u0097\7t\2\2\u0097\u0098"+
        "\7k\2\2\u0098\u0099\7i\2\2\u0099\u009a\7i\2\2\u009a\u009b\7g\2\2\u009b"+
        "\u009c\7t\2\2\u009c\36\3\2\2\2\u009d\u009e\7c\2\2\u009e\u009f\7e\2\2\u009f"+
        "\u00a0\7v\2\2\u00a0\u00a1\7k\2\2\u00a1\u00a2\7q\2\2\u00a2\u00a3\7p\2\2"+
        "\u00a3 \3\2\2\2\u00a4\u00a5\7d\2\2\u00a5\u00a6\7c\2\2\u00a6\u00a7\7n\2"+
        "\2\u00a7\u00a8\7n\2\2\u00a8\"\3\2\2\2\u00a9\u00aa\7u\2\2\u00aa\u00ab\7"+
        "s\2\2\u00ab\u00ac\7w\2\2\u00ac\u00ad\7c\2\2\u00ad\u00ae\7t\2\2\u00ae\u00af"+
        "\7g\2\2\u00af\u00b0\7D\2\2\u00b0\u00b1\7w\2\2\u00b1\u00b2\7o\2\2\u00b2"+
        "\u00b3\7r\2\2\u00b3\u00b4\7g\2\2\u00b4\u00b5\7t\2\2\u00b5$\3\2\2\2\u00b6"+
        "\u00b7\7e\2\2\u00b7\u00b8\7k\2\2\u00b8\u00b9\7t\2\2\u00b9\u00ba\7e\2\2"+
        "\u00ba\u00bb\7n\2\2\u00bb\u00bc\7g\2\2\u00bc\u00bd\7D\2\2\u00bd\u00be"+
        "\7w\2\2\u00be\u00bf\7o\2\2\u00bf\u00c0\7r\2\2\u00c0\u00c1\7g\2\2\u00c1"+
        "\u00c2\7t\2\2\u00c2&\3\2\2\2\u00c3\u00c4\7v\2\2\u00c4\u00c5\7t\2\2\u00c5"+
        "\u00c6\7k\2\2\u00c6\u00c7\7c\2\2\u00c7\u00c8\7p\2\2\u00c8\u00c9\7i\2\2"+
        "\u00c9\u00ca\7n\2\2\u00ca\u00cb\7g\2\2\u00cb\u00cc\7D\2\2\u00cc\u00cd"+
        "\7w\2\2\u00cd\u00ce\7o\2\2\u00ce\u00cf\7r\2\2\u00cf\u00d0\7g\2\2\u00d0"+
        "\u00d1\7t\2\2\u00d1(\3\2\2\2\u00d2\u00d3\7n\2\2\u00d3\u00d4\7g\2\2\u00d4"+
        "\u00d5\7h\2\2\u00d5\u00d6\7v\2\2\u00d6\u00d7\7H\2\2\u00d7\u00d8\7n\2\2"+
        "\u00d8\u00d9\7k\2\2\u00d9\u00da\7r\2\2\u00da\u00db\7r\2\2\u00db\u00dc"+
        "\7g\2\2\u00dc\u00dd\7t\2\2\u00dd*\3\2\2\2\u00de\u00df\7t\2\2\u00df\u00e0"+
        "\7k\2\2\u00e0\u00e1\7i\2\2\u00e1\u00e2\7j\2\2\u00e2\u00e3\7v\2\2\u00e3"+
        "\u00e4\7H\2\2\u00e4\u00e5\7n\2\2\u00e5\u00e6\7k\2\2\u00e6\u00e7\7r\2\2"+
        "\u00e7\u00e8\7r\2\2\u00e8\u00e9\7g\2\2\u00e9\u00ea\7t\2\2\u00ea,\3\2\2"+
        "\2\u00eb\u00ec\7c\2\2\u00ec\u00ed\7d\2\2\u00ed\u00ee\7u\2\2\u00ee\u00ef"+
        "\7q\2\2\u00ef\u00f0\7t\2\2\u00f0\u00f1\7d\2\2\u00f1\u00f2\7g\2\2\u00f2"+
        "\u00f3\7t\2\2\u00f3.\3\2\2\2\u00f4\u00f5\7h\2\2\u00f5\u00f6\7k\2\2\u00f6"+
        "\u00f7\7t\2\2\u00f7\u00f8\7g\2\2\u00f8\60\3\2\2\2\u00f9\u00fb\4\62;\2"+
        "\u00fa\u00f9\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc\u00fa\3\2\2\2\u00fc\u00fd"+
        "\3\2\2\2\u00fd\62\3\2\2\2\u00fe\u0100\7/\2\2\u00ff\u00fe\3\2\2\2\u00ff"+
        "\u0100\3\2\2\2\u0100\u0115\3\2\2\2\u0101\u0103\4\62;\2\u0102\u0101\3\2"+
        "\2\2\u0103\u0104\3\2\2\2\u0104\u0102\3\2\2\2\u0104\u0105\3\2\2\2\u0105"+
        "\u0106\3\2\2\2\u0106\u010a\7\60\2\2\u0107\u0109\4\62;\2\u0108\u0107\3"+
        "\2\2\2\u0109\u010c\3\2\2\2\u010a\u0108\3\2\2\2\u010a\u010b\3\2\2\2\u010b"+
        "\u0116\3\2\2\2\u010c\u010a\3\2\2\2\u010d\u010f\7\60\2\2\u010e\u010d\3"+
        "\2\2\2\u010e\u010f\3\2\2\2\u010f\u0111\3\2\2\2\u0110\u0112\4\62;\2\u0111"+
        "\u0110\3\2\2\2\u0112\u0113\3\2\2\2\u0113\u0111\3\2\2\2\u0113\u0114\3\2"+
        "\2\2\u0114\u0116\3\2\2\2\u0115\u0102\3\2\2\2\u0115\u010e\3\2\2\2\u0116"+
        "\64\3\2\2\2\u0117\u0119\t\2\2\2\u0118\u0117\3\2\2\2\u0119\u011d\3\2\2"+
        "\2\u011a\u011c\t\3\2\2\u011b\u011a\3\2\2\2\u011c\u011f\3\2\2\2\u011d\u011b"+
        "\3\2\2\2\u011d\u011e\3\2\2\2\u011e\66\3\2\2\2\u011f\u011d\3\2\2\2\u0120"+
        "\u0122\t\4\2\2\u0121\u0120\3\2\2\2\u0122\u0123\3\2\2\2\u0123\u0121\3\2"+
        "\2\2\u0123\u0124\3\2\2\2\u0124\u0125\3\2\2\2\u0125\u0126\b\34\2\2\u0126"+
        "8\3\2\2\2\u0127\u012b\7%\2\2\u0128\u012a\n\5\2\2\u0129\u0128\3\2\2\2\u012a"+
        "\u012d\3\2\2\2\u012b\u0129\3\2\2\2\u012b\u012c\3\2\2\2\u012c\u012e\3\2"+
        "\2\2\u012d\u012b\3\2\2\2\u012e\u012f\b\35\3\2\u012f:\3\2\2\2\17\2\u00fc"+
        "\u00ff\u0104\u010a\u010e\u0113\u0115\u0118\u011b\u011d\u0123\u012b";
    public static final ATN _ATN =
        ATNSimulator.deserialize(_serializedATN.toCharArray());
    static {
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
    }
}
