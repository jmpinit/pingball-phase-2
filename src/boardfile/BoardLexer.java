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
		X=9, Y=10, ORT=11, WIDTH=12, HEIGHT=13, TRIGGER=14, ACTION=15, KEYLABEL=16, 
		OTHBOARDL=17, OTHPORTL=18, BALL=19, SQB=20, CCB=21, TRIB=22, LEFTF=23, 
		RIGHTF=24, ABS=25, FIRE=26, KEYL=27, PORTAL=28, KEY=29, INT=30, FLOAT=31, 
		NAME=32, WHITESPACE=33, COMMENT=34;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'board'", "'='", "'name'", "'gravity'", "'friction1'", "'friction2'", 
		"'xVelocity'", "'yVelocity'", "'x'", "'y'", "'orientation'", "'width'", 
		"'height'", "'trigger'", "'action'", "'key'", "'otherBoard'", "'otherPortal'", 
		"'ball'", "'squareBumper'", "'circleBumper'", "'triangleBumper'", "'leftFlipper'", 
		"'rightFlipper'", "'absorber'", "'fire'", "KEYL", "'portal'", "KEY", "INT", 
		"FLOAT", "NAME", "WHITESPACE", "COMMENT"
	};
	public static final String[] ruleNames = {
		"BOARDW", "EQ", "NAMEL", "GRAVITY", "FRICTION1", "FRICTION2", "XV", "YV", 
		"X", "Y", "ORT", "WIDTH", "HEIGHT", "TRIGGER", "ACTION", "KEYLABEL", "OTHBOARDL", 
		"OTHPORTL", "BALL", "SQB", "CCB", "TRIB", "LEFTF", "RIGHTF", "ABS", "FIRE", 
		"KEYL", "PORTAL", "KEY", "INT", "FLOAT", "NAME", "WHITESPACE", "COMMENT"
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
	    
	    private boolean lookingForKey = false;


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
		case 26: KEYL_action((RuleContext)_localctx, actionIndex); break;

		case 28: KEY_action((RuleContext)_localctx, actionIndex); break;

		case 32: WHITESPACE_action((RuleContext)_localctx, actionIndex); break;

		case 33: COMMENT_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WHITESPACE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2: skip();  break;
		}
	}
	private void COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3: skip();  break;
		}
	}
	private void KEY_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1: lookingForKey = false; break;
		}
	}
	private void KEYL_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: lookingForKey = true; break;
		}
	}
	@Override
	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 28: return KEY_sempred((RuleContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean KEY_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return lookingForKey;
		}
		return true;
	}

	public static final String _serializedATN =
		"\2\4$\u01ef\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t"+
		"\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20"+
		"\t\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27"+
		"\t\27\4\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36"+
		"\t\36\4\37\t\37\4 \t \4!\t!\4\"\t\"\4#\t#\3\2\3\2\3\2\3\2\3\2\3\2\3\3"+
		"\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21"+
		"\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24"+
		"\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\34"+
		"\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\5\34\u012d\n\34"+
		"\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\5\36\u01b5\n\36\3\36\3\36\3\37"+
		"\6\37\u01ba\n\37\r\37\16\37\u01bb\3 \5 \u01bf\n \3 \6 \u01c2\n \r \16"+
		" \u01c3\3 \3 \7 \u01c8\n \f \16 \u01cb\13 \3 \5 \u01ce\n \3 \6 \u01d1"+
		"\n \r \16 \u01d2\5 \u01d5\n \3!\5!\u01d8\n!\3!\7!\u01db\n!\f!\16!\u01de"+
		"\13!\3\"\6\"\u01e1\n\"\r\"\16\"\u01e2\3\"\3\"\3#\3#\7#\u01e9\n#\f#\16"+
		"#\u01ec\13#\3#\3#\2$\3\3\1\5\4\1\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n"+
		"\1\23\13\1\25\f\1\27\r\1\31\16\1\33\17\1\35\20\1\37\21\1!\22\1#\23\1%"+
		"\24\1\'\25\1)\26\1+\27\1-\30\1/\31\1\61\32\1\63\33\1\65\34\1\67\35\29"+
		"\36\1;\37\3= \1?!\1A\"\1C#\4E$\5\3\2\7\4\62;c|\5C\\aac|\6\62;C\\aac|\5"+
		"\13\f\17\17\"\"\4\f\f\17\17\u020e\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2"+
		"\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3"+
		"\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2"+
		"\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2"+
		"\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2"+
		"\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2"+
		"\2\2C\3\2\2\2\2E\3\2\2\2\3G\3\2\2\2\5M\3\2\2\2\7O\3\2\2\2\tT\3\2\2\2\13"+
		"\\\3\2\2\2\rf\3\2\2\2\17p\3\2\2\2\21z\3\2\2\2\23\u0084\3\2\2\2\25\u0086"+
		"\3\2\2\2\27\u0088\3\2\2\2\31\u0094\3\2\2\2\33\u009a\3\2\2\2\35\u00a1\3"+
		"\2\2\2\37\u00a9\3\2\2\2!\u00b0\3\2\2\2#\u00b4\3\2\2\2%\u00bf\3\2\2\2\'"+
		"\u00cb\3\2\2\2)\u00d0\3\2\2\2+\u00dd\3\2\2\2-\u00ea\3\2\2\2/\u00f9\3\2"+
		"\2\2\61\u0105\3\2\2\2\63\u0112\3\2\2\2\65\u011b\3\2\2\2\67\u012c\3\2\2"+
		"\29\u0130\3\2\2\2;\u0137\3\2\2\2=\u01b9\3\2\2\2?\u01be\3\2\2\2A\u01d7"+
		"\3\2\2\2C\u01e0\3\2\2\2E\u01e6\3\2\2\2GH\7d\2\2HI\7q\2\2IJ\7c\2\2JK\7"+
		"t\2\2KL\7f\2\2L\4\3\2\2\2MN\7?\2\2N\6\3\2\2\2OP\7p\2\2PQ\7c\2\2QR\7o\2"+
		"\2RS\7g\2\2S\b\3\2\2\2TU\7i\2\2UV\7t\2\2VW\7c\2\2WX\7x\2\2XY\7k\2\2YZ"+
		"\7v\2\2Z[\7{\2\2[\n\3\2\2\2\\]\7h\2\2]^\7t\2\2^_\7k\2\2_`\7e\2\2`a\7v"+
		"\2\2ab\7k\2\2bc\7q\2\2cd\7p\2\2de\7\63\2\2e\f\3\2\2\2fg\7h\2\2gh\7t\2"+
		"\2hi\7k\2\2ij\7e\2\2jk\7v\2\2kl\7k\2\2lm\7q\2\2mn\7p\2\2no\7\64\2\2o\16"+
		"\3\2\2\2pq\7z\2\2qr\7X\2\2rs\7g\2\2st\7n\2\2tu\7q\2\2uv\7e\2\2vw\7k\2"+
		"\2wx\7v\2\2xy\7{\2\2y\20\3\2\2\2z{\7{\2\2{|\7X\2\2|}\7g\2\2}~\7n\2\2~"+
		"\177\7q\2\2\177\u0080\7e\2\2\u0080\u0081\7k\2\2\u0081\u0082\7v\2\2\u0082"+
		"\u0083\7{\2\2\u0083\22\3\2\2\2\u0084\u0085\7z\2\2\u0085\24\3\2\2\2\u0086"+
		"\u0087\7{\2\2\u0087\26\3\2\2\2\u0088\u0089\7q\2\2\u0089\u008a\7t\2\2\u008a"+
		"\u008b\7k\2\2\u008b\u008c\7g\2\2\u008c\u008d\7p\2\2\u008d\u008e\7v\2\2"+
		"\u008e\u008f\7c\2\2\u008f\u0090\7v\2\2\u0090\u0091\7k\2\2\u0091\u0092"+
		"\7q\2\2\u0092\u0093\7p\2\2\u0093\30\3\2\2\2\u0094\u0095\7y\2\2\u0095\u0096"+
		"\7k\2\2\u0096\u0097\7f\2\2\u0097\u0098\7v\2\2\u0098\u0099\7j\2\2\u0099"+
		"\32\3\2\2\2\u009a\u009b\7j\2\2\u009b\u009c\7g\2\2\u009c\u009d\7k\2\2\u009d"+
		"\u009e\7i\2\2\u009e\u009f\7j\2\2\u009f\u00a0\7v\2\2\u00a0\34\3\2\2\2\u00a1"+
		"\u00a2\7v\2\2\u00a2\u00a3\7t\2\2\u00a3\u00a4\7k\2\2\u00a4\u00a5\7i\2\2"+
		"\u00a5\u00a6\7i\2\2\u00a6\u00a7\7g\2\2\u00a7\u00a8\7t\2\2\u00a8\36\3\2"+
		"\2\2\u00a9\u00aa\7c\2\2\u00aa\u00ab\7e\2\2\u00ab\u00ac\7v\2\2\u00ac\u00ad"+
		"\7k\2\2\u00ad\u00ae\7q\2\2\u00ae\u00af\7p\2\2\u00af \3\2\2\2\u00b0\u00b1"+
		"\7m\2\2\u00b1\u00b2\7g\2\2\u00b2\u00b3\7{\2\2\u00b3\"\3\2\2\2\u00b4\u00b5"+
		"\7q\2\2\u00b5\u00b6\7v\2\2\u00b6\u00b7\7j\2\2\u00b7\u00b8\7g\2\2\u00b8"+
		"\u00b9\7t\2\2\u00b9\u00ba\7D\2\2\u00ba\u00bb\7q\2\2\u00bb\u00bc\7c\2\2"+
		"\u00bc\u00bd\7t\2\2\u00bd\u00be\7f\2\2\u00be$\3\2\2\2\u00bf\u00c0\7q\2"+
		"\2\u00c0\u00c1\7v\2\2\u00c1\u00c2\7j\2\2\u00c2\u00c3\7g\2\2\u00c3\u00c4"+
		"\7t\2\2\u00c4\u00c5\7R\2\2\u00c5\u00c6\7q\2\2\u00c6\u00c7\7t\2\2\u00c7"+
		"\u00c8\7v\2\2\u00c8\u00c9\7c\2\2\u00c9\u00ca\7n\2\2\u00ca&\3\2\2\2\u00cb"+
		"\u00cc\7d\2\2\u00cc\u00cd\7c\2\2\u00cd\u00ce\7n\2\2\u00ce\u00cf\7n\2\2"+
		"\u00cf(\3\2\2\2\u00d0\u00d1\7u\2\2\u00d1\u00d2\7s\2\2\u00d2\u00d3\7w\2"+
		"\2\u00d3\u00d4\7c\2\2\u00d4\u00d5\7t\2\2\u00d5\u00d6\7g\2\2\u00d6\u00d7"+
		"\7D\2\2\u00d7\u00d8\7w\2\2\u00d8\u00d9\7o\2\2\u00d9\u00da\7r\2\2\u00da"+
		"\u00db\7g\2\2\u00db\u00dc\7t\2\2\u00dc*\3\2\2\2\u00dd\u00de\7e\2\2\u00de"+
		"\u00df\7k\2\2\u00df\u00e0\7t\2\2\u00e0\u00e1\7e\2\2\u00e1\u00e2\7n\2\2"+
		"\u00e2\u00e3\7g\2\2\u00e3\u00e4\7D\2\2\u00e4\u00e5\7w\2\2\u00e5\u00e6"+
		"\7o\2\2\u00e6\u00e7\7r\2\2\u00e7\u00e8\7g\2\2\u00e8\u00e9\7t\2\2\u00e9"+
		",\3\2\2\2\u00ea\u00eb\7v\2\2\u00eb\u00ec\7t\2\2\u00ec\u00ed\7k\2\2\u00ed"+
		"\u00ee\7c\2\2\u00ee\u00ef\7p\2\2\u00ef\u00f0\7i\2\2\u00f0\u00f1\7n\2\2"+
		"\u00f1\u00f2\7g\2\2\u00f2\u00f3\7D\2\2\u00f3\u00f4\7w\2\2\u00f4\u00f5"+
		"\7o\2\2\u00f5\u00f6\7r\2\2\u00f6\u00f7\7g\2\2\u00f7\u00f8\7t\2\2\u00f8"+
		".\3\2\2\2\u00f9\u00fa\7n\2\2\u00fa\u00fb\7g\2\2\u00fb\u00fc\7h\2\2\u00fc"+
		"\u00fd\7v\2\2\u00fd\u00fe\7H\2\2\u00fe\u00ff\7n\2\2\u00ff\u0100\7k\2\2"+
		"\u0100\u0101\7r\2\2\u0101\u0102\7r\2\2\u0102\u0103\7g\2\2\u0103\u0104"+
		"\7t\2\2\u0104\60\3\2\2\2\u0105\u0106\7t\2\2\u0106\u0107\7k\2\2\u0107\u0108"+
		"\7i\2\2\u0108\u0109\7j\2\2\u0109\u010a\7v\2\2\u010a\u010b\7H\2\2\u010b"+
		"\u010c\7n\2\2\u010c\u010d\7k\2\2\u010d\u010e\7r\2\2\u010e\u010f\7r\2\2"+
		"\u010f\u0110\7g\2\2\u0110\u0111\7t\2\2\u0111\62\3\2\2\2\u0112\u0113\7"+
		"c\2\2\u0113\u0114\7d\2\2\u0114\u0115\7u\2\2\u0115\u0116\7q\2\2\u0116\u0117"+
		"\7t\2\2\u0117\u0118\7d\2\2\u0118\u0119\7g\2\2\u0119\u011a\7t\2\2\u011a"+
		"\64\3\2\2\2\u011b\u011c\7h\2\2\u011c\u011d\7k\2\2\u011d\u011e\7t\2\2\u011e"+
		"\u011f\7g\2\2\u011f\66\3\2\2\2\u0120\u0121\7m\2\2\u0121\u0122\7g\2\2\u0122"+
		"\u0123\7{\2\2\u0123\u0124\7w\2\2\u0124\u012d\7r\2\2\u0125\u0126\7m\2\2"+
		"\u0126\u0127\7g\2\2\u0127\u0128\7{\2\2\u0128\u0129\7f\2\2\u0129\u012a"+
		"\7q\2\2\u012a\u012b\7y\2\2\u012b\u012d\7p\2\2\u012c\u0120\3\2\2\2\u012c"+
		"\u0125\3\2\2\2\u012d\u012e\3\2\2\2\u012e\u012f\b\34\2\2\u012f8\3\2\2\2"+
		"\u0130\u0131\7r\2\2\u0131\u0132\7q\2\2\u0132\u0133\7t\2\2\u0133\u0134"+
		"\7v\2\2\u0134\u0135\7c\2\2\u0135\u0136\7n\2\2\u0136:\3\2\2\2\u0137\u01b4"+
		"\6\36\2\2\u0138\u01b5\t\2\2\2\u0139\u013a\7u\2\2\u013a\u013b\7j\2\2\u013b"+
		"\u013c\7k\2\2\u013c\u013d\7h\2\2\u013d\u01b5\7v\2\2\u013e\u013f\7e\2\2"+
		"\u013f\u0140\7v\2\2\u0140\u0141\7t\2\2\u0141\u01b5\7n\2\2\u0142\u0143"+
		"\7c\2\2\u0143\u0144\7n\2\2\u0144\u01b5\7v\2\2\u0145\u0146\7o\2\2\u0146"+
		"\u0147\7g\2\2\u0147\u0148\7v\2\2\u0148\u01b5\7c\2\2\u0149\u014a\7u\2\2"+
		"\u014a\u014b\7r\2\2\u014b\u014c\7c\2\2\u014c\u014d\7e\2\2\u014d\u01b5"+
		"\7g\2\2\u014e\u014f\7n\2\2\u014f\u0150\7g\2\2\u0150\u0151\7h\2\2\u0151"+
		"\u01b5\7v\2\2\u0152\u0153\7t\2\2\u0153\u0154\7k\2\2\u0154\u0155\7i\2\2"+
		"\u0155\u0156\7j\2\2\u0156\u01b5\7v\2\2\u0157\u0158\7w\2\2\u0158\u01b5"+
		"\7r\2\2\u0159\u015a\7f\2\2\u015a\u015b\7q\2\2\u015b\u015c\7y\2\2\u015c"+
		"\u01b5\7p\2\2\u015d\u015e\7o\2\2\u015e\u015f\7k\2\2\u015f\u0160\7p\2\2"+
		"\u0160\u0161\7w\2\2\u0161\u01b5\7u\2\2\u0162\u0163\7g\2\2\u0163\u0164"+
		"\7s\2\2\u0164\u0165\7w\2\2\u0165\u0166\7c\2\2\u0166\u0167\7n\2\2\u0167"+
		"\u01b5\7u\2\2\u0168\u0169\7d\2\2\u0169\u016a\7c\2\2\u016a\u016b\7e\2\2"+
		"\u016b\u016c\7m\2\2\u016c\u016d\7u\2\2\u016d\u016e\7r\2\2\u016e\u016f"+
		"\7c\2\2\u016f\u0170\7e\2\2\u0170\u01b5\7g\2\2\u0171\u0172\7q\2\2\u0172"+
		"\u0173\7r\2\2\u0173\u0174\7g\2\2\u0174\u0175\7p\2\2\u0175\u0176\7d\2\2"+
		"\u0176\u0177\7t\2\2\u0177\u0178\7c\2\2\u0178\u0179\7e\2\2\u0179\u017a"+
		"\7m\2\2\u017a\u017b\7g\2\2\u017b\u01b5\7v\2\2\u017c\u017d\7e\2\2\u017d"+
		"\u017e\7n\2\2\u017e\u017f\7q\2\2\u017f\u0180\7u\2\2\u0180\u0181\7g\2\2"+
		"\u0181\u0182\7d\2\2\u0182\u0183\7t\2\2\u0183\u0184\7c\2\2\u0184\u0185"+
		"\7e\2\2\u0185\u0186\7m\2\2\u0186\u0187\7g\2\2\u0187\u01b5\7v\2\2\u0188"+
		"\u0189\7d\2\2\u0189\u018a\7c\2\2\u018a\u018b\7e\2\2\u018b\u018c\7m\2\2"+
		"\u018c\u018d\7u\2\2\u018d\u018e\7n\2\2\u018e\u018f\7c\2\2\u018f\u0190"+
		"\7u\2\2\u0190\u01b5\7j\2\2\u0191\u0192\7u\2\2\u0192\u0193\7g\2\2\u0193"+
		"\u0194\7o\2\2\u0194\u0195\7k\2\2\u0195\u0196\7e\2\2\u0196\u0197\7q\2\2"+
		"\u0197\u0198\7n\2\2\u0198\u0199\7q\2\2\u0199\u01b5\7p\2\2\u019a\u019b"+
		"\7s\2\2\u019b\u019c\7w\2\2\u019c\u019d\7q\2\2\u019d\u019e\7v\2\2\u019e"+
		"\u01b5\7g\2\2\u019f\u01a0\7g\2\2\u01a0\u01a1\7p\2\2\u01a1\u01a2\7v\2\2"+
		"\u01a2\u01a3\7g\2\2\u01a3\u01b5\7t\2\2\u01a4\u01a5\7e\2\2\u01a5\u01a6"+
		"\7q\2\2\u01a6\u01a7\7o\2\2\u01a7\u01a8\7o\2\2\u01a8\u01b5\7c\2\2\u01a9"+
		"\u01aa\7r\2\2\u01aa\u01ab\7g\2\2\u01ab\u01ac\7t\2\2\u01ac\u01ad\7k\2\2"+
		"\u01ad\u01ae\7q\2\2\u01ae\u01b5\7f\2\2\u01af\u01b0\7u\2\2\u01b0\u01b1"+
		"\7n\2\2\u01b1\u01b2\7c\2\2\u01b2\u01b3\7u\2\2\u01b3\u01b5\7j\2\2\u01b4"+
		"\u0138\3\2\2\2\u01b4\u0139\3\2\2\2\u01b4\u013e\3\2\2\2\u01b4\u0142\3\2"+
		"\2\2\u01b4\u0145\3\2\2\2\u01b4\u0149\3\2\2\2\u01b4\u014e\3\2\2\2\u01b4"+
		"\u0152\3\2\2\2\u01b4\u0157\3\2\2\2\u01b4\u0159\3\2\2\2\u01b4\u015d\3\2"+
		"\2\2\u01b4\u0162\3\2\2\2\u01b4\u0168\3\2\2\2\u01b4\u0171\3\2\2\2\u01b4"+
		"\u017c\3\2\2\2\u01b4\u0188\3\2\2\2\u01b4\u0191\3\2\2\2\u01b4\u019a\3\2"+
		"\2\2\u01b4\u019f\3\2\2\2\u01b4\u01a4\3\2\2\2\u01b4\u01a9\3\2\2\2\u01b4"+
		"\u01af\3\2\2\2\u01b5\u01b6\3\2\2\2\u01b6\u01b7\b\36\3\2\u01b7<\3\2\2\2"+
		"\u01b8\u01ba\4\62;\2\u01b9\u01b8\3\2\2\2\u01ba\u01bb\3\2\2\2\u01bb\u01b9"+
		"\3\2\2\2\u01bb\u01bc\3\2\2\2\u01bc>\3\2\2\2\u01bd\u01bf\7/\2\2\u01be\u01bd"+
		"\3\2\2\2\u01be\u01bf\3\2\2\2\u01bf\u01d4\3\2\2\2\u01c0\u01c2\4\62;\2\u01c1"+
		"\u01c0\3\2\2\2\u01c2\u01c3\3\2\2\2\u01c3\u01c1\3\2\2\2\u01c3\u01c4\3\2"+
		"\2\2\u01c4\u01c5\3\2\2\2\u01c5\u01c9\7\60\2\2\u01c6\u01c8\4\62;\2\u01c7"+
		"\u01c6\3\2\2\2\u01c8\u01cb\3\2\2\2\u01c9\u01c7\3\2\2\2\u01c9\u01ca\3\2"+
		"\2\2\u01ca\u01d5\3\2\2\2\u01cb\u01c9\3\2\2\2\u01cc\u01ce\7\60\2\2\u01cd"+
		"\u01cc\3\2\2\2\u01cd\u01ce\3\2\2\2\u01ce\u01d0\3\2\2\2\u01cf\u01d1\4\62"+
		";\2\u01d0\u01cf\3\2\2\2\u01d1\u01d2\3\2\2\2\u01d2\u01d0\3\2\2\2\u01d2"+
		"\u01d3\3\2\2\2\u01d3\u01d5\3\2\2\2\u01d4\u01c1\3\2\2\2\u01d4\u01cd\3\2"+
		"\2\2\u01d5@\3\2\2\2\u01d6\u01d8\t\3\2\2\u01d7\u01d6\3\2\2\2\u01d8\u01dc"+
		"\3\2\2\2\u01d9\u01db\t\4\2\2\u01da\u01d9\3\2\2\2\u01db\u01de\3\2\2\2\u01dc"+
		"\u01da\3\2\2\2\u01dc\u01dd\3\2\2\2\u01ddB\3\2\2\2\u01de\u01dc\3\2\2\2"+
		"\u01df\u01e1\t\5\2\2\u01e0\u01df\3\2\2\2\u01e1\u01e2\3\2\2\2\u01e2\u01e0"+
		"\3\2\2\2\u01e2\u01e3\3\2\2\2\u01e3\u01e4\3\2\2\2\u01e4\u01e5\b\"\4\2\u01e5"+
		"D\3\2\2\2\u01e6\u01ea\7%\2\2\u01e7\u01e9\n\6\2\2\u01e8\u01e7\3\2\2\2\u01e9"+
		"\u01ec\3\2\2\2\u01ea\u01e8\3\2\2\2\u01ea\u01eb\3\2\2\2\u01eb\u01ed\3\2"+
		"\2\2\u01ec\u01ea\3\2\2\2\u01ed\u01ee\b#\5\2\u01eeF\3\2\2\2\21\2\u012c"+
		"\u01b4\u01bb\u01be\u01c3\u01c9\u01cd\u01d2\u01d4\u01d7\u01da\u01dc\u01e2"+
		"\u01ea";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}