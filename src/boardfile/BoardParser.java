// Generated from Board.g4 by ANTLR 4.0

package boardfile;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BoardParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BOARDW=1, EQ=2, NAMEL=3, GRAVITY=4, FRICTION1=5, FRICTION2=6, XV=7, YV=8, 
		X=9, Y=10, ORT=11, WIDTH=12, HEIGHT=13, TRIGGER=14, ACTION=15, BALL=16, 
		SQB=17, CCB=18, TRIB=19, LEFTF=20, RIGHTF=21, ABS=22, FIRE=23, INT=24, 
		FLOAT=25, NAME=26, WHITESPACE=27, COMMENT=28;
	public static final String[] tokenNames = {
		"<INVALID>", "'board'", "'='", "'name'", "'gravity'", "'friction1'", "'friction2'", 
		"'xVelocity'", "'yVelocity'", "'x'", "'y'", "'orientation'", "'width'", 
		"'height'", "'trigger'", "'action'", "'ball'", "'squareBumper'", "'circleBumper'", 
		"'triangleBumper'", "'leftFlipper'", "'rightFlipper'", "'absorber'", "'fire'", 
		"INT", "FLOAT", "NAME", "WHITESPACE", "COMMENT"
	};
	public static final int
		RULE_board = 0, RULE_topline = 1, RULE_lines = 2, RULE_line = 3, RULE_ballline = 4, 
		RULE_sqbline = 5, RULE_ccbline = 6, RULE_tribline = 7, RULE_leftfline = 8, 
		RULE_rightfline = 9, RULE_absline = 10, RULE_fireline = 11, RULE_namefield = 12, 
		RULE_gravityfield = 13, RULE_friction1field = 14, RULE_friction2field = 15, 
		RULE_xffield = 16, RULE_yffield = 17, RULE_xvfield = 18, RULE_yvfield = 19, 
		RULE_xfield = 20, RULE_yfield = 21, RULE_ortfield = 22, RULE_widthfield = 23, 
		RULE_heightfield = 24, RULE_triggerfield = 25, RULE_actionfield = 26;
	public static final String[] ruleNames = {
		"board", "topline", "lines", "line", "ballline", "sqbline", "ccbline", 
		"tribline", "leftfline", "rightfline", "absline", "fireline", "namefield", 
		"gravityfield", "friction1field", "friction2field", "xffield", "yffield", 
		"xvfield", "yvfield", "xfield", "yfield", "ortfield", "widthfield", "heightfield", 
		"triggerfield", "actionfield"
	};

	@Override
	public String getGrammarFileName() { return "Board.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }


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

	public BoardParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class BoardContext extends ParserRuleContext {
		public ToplineContext topline() {
			return getRuleContext(ToplineContext.class,0);
		}
		public TerminalNode EOF() { return getToken(BoardParser.EOF, 0); }
		public LinesContext lines() {
			return getRuleContext(LinesContext.class,0);
		}
		public BoardContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_board; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterBoard(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitBoard(this);
		}
	}

	public final BoardContext board() throws RecognitionException {
		BoardContext _localctx = new BoardContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_board);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54); topline();
			setState(55); lines(0);
			setState(56); match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ToplineContext extends ParserRuleContext {
		public TerminalNode BOARDW() { return getToken(BoardParser.BOARDW, 0); }
		public Friction1fieldContext friction1field() {
			return getRuleContext(Friction1fieldContext.class,0);
		}
		public GravityfieldContext gravityfield() {
			return getRuleContext(GravityfieldContext.class,0);
		}
		public NamefieldContext namefield() {
			return getRuleContext(NamefieldContext.class,0);
		}
		public Friction2fieldContext friction2field() {
			return getRuleContext(Friction2fieldContext.class,0);
		}
		public ToplineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_topline; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterTopline(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitTopline(this);
		}
	}

	public final ToplineContext topline() throws RecognitionException {
		ToplineContext _localctx = new ToplineContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_topline);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58); match(BOARDW);
			setState(59); namefield();
			setState(61);
			_la = _input.LA(1);
			if (_la==GRAVITY) {
				{
				setState(60); gravityfield();
				}
			}

			setState(64);
			_la = _input.LA(1);
			if (_la==FRICTION1) {
				{
				setState(63); friction1field();
				}
			}

			setState(67);
			_la = _input.LA(1);
			if (_la==FRICTION2) {
				{
				setState(66); friction2field();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LinesContext extends ParserRuleContext {
		public int _p;
		public LineContext line() {
			return getRuleContext(LineContext.class,0);
		}
		public LinesContext lines() {
			return getRuleContext(LinesContext.class,0);
		}
		public LinesContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public LinesContext(ParserRuleContext parent, int invokingState, int _p) {
			super(parent, invokingState);
			this._p = _p;
		}
		@Override public int getRuleIndex() { return RULE_lines; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterLines(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitLines(this);
		}
	}

	public final LinesContext lines(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		LinesContext _localctx = new LinesContext(_ctx, _parentState, _p);
		LinesContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, RULE_lines);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(70); line();
			}
			_ctx.stop = _input.LT(-1);
			setState(76);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new LinesContext(_parentctx, _parentState, _p);
					pushNewRecursionContext(_localctx, _startState, RULE_lines);
					setState(72);
					if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
					setState(73); line();
					}
					} 
				}
				setState(78);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class LineContext extends ParserRuleContext {
		public RightflineContext rightfline() {
			return getRuleContext(RightflineContext.class,0);
		}
		public SqblineContext sqbline() {
			return getRuleContext(SqblineContext.class,0);
		}
		public BalllineContext ballline() {
			return getRuleContext(BalllineContext.class,0);
		}
		public AbslineContext absline() {
			return getRuleContext(AbslineContext.class,0);
		}
		public TriblineContext tribline() {
			return getRuleContext(TriblineContext.class,0);
		}
		public FirelineContext fireline() {
			return getRuleContext(FirelineContext.class,0);
		}
		public LeftflineContext leftfline() {
			return getRuleContext(LeftflineContext.class,0);
		}
		public CcblineContext ccbline() {
			return getRuleContext(CcblineContext.class,0);
		}
		public LineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_line; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitLine(this);
		}
	}

	public final LineContext line() throws RecognitionException {
		LineContext _localctx = new LineContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_line);
		try {
			setState(87);
			switch (_input.LA(1)) {
			case BALL:
				enterOuterAlt(_localctx, 1);
				{
				setState(79); ballline();
				}
				break;
			case SQB:
				enterOuterAlt(_localctx, 2);
				{
				setState(80); sqbline();
				}
				break;
			case CCB:
				enterOuterAlt(_localctx, 3);
				{
				setState(81); ccbline();
				}
				break;
			case TRIB:
				enterOuterAlt(_localctx, 4);
				{
				setState(82); tribline();
				}
				break;
			case LEFTF:
				enterOuterAlt(_localctx, 5);
				{
				setState(83); leftfline();
				}
				break;
			case RIGHTF:
				enterOuterAlt(_localctx, 6);
				{
				setState(84); rightfline();
				}
				break;
			case ABS:
				enterOuterAlt(_localctx, 7);
				{
				setState(85); absline();
				}
				break;
			case FIRE:
				enterOuterAlt(_localctx, 8);
				{
				setState(86); fireline();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BalllineContext extends ParserRuleContext {
		public YffieldContext yffield() {
			return getRuleContext(YffieldContext.class,0);
		}
		public YvfieldContext yvfield() {
			return getRuleContext(YvfieldContext.class,0);
		}
		public TerminalNode BALL() { return getToken(BoardParser.BALL, 0); }
		public XffieldContext xffield() {
			return getRuleContext(XffieldContext.class,0);
		}
		public NamefieldContext namefield() {
			return getRuleContext(NamefieldContext.class,0);
		}
		public XvfieldContext xvfield() {
			return getRuleContext(XvfieldContext.class,0);
		}
		public BalllineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ballline; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterBallline(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitBallline(this);
		}
	}

	public final BalllineContext ballline() throws RecognitionException {
		BalllineContext _localctx = new BalllineContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_ballline);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89); match(BALL);
			setState(90); namefield();
			setState(91); xffield();
			setState(92); yffield();
			setState(93); xvfield();
			setState(94); yvfield();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SqblineContext extends ParserRuleContext {
		public YfieldContext yfield() {
			return getRuleContext(YfieldContext.class,0);
		}
		public XfieldContext xfield() {
			return getRuleContext(XfieldContext.class,0);
		}
		public TerminalNode SQB() { return getToken(BoardParser.SQB, 0); }
		public NamefieldContext namefield() {
			return getRuleContext(NamefieldContext.class,0);
		}
		public SqblineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sqbline; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterSqbline(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitSqbline(this);
		}
	}

	public final SqblineContext sqbline() throws RecognitionException {
		SqblineContext _localctx = new SqblineContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_sqbline);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96); match(SQB);
			setState(97); namefield();
			setState(98); xfield();
			setState(99); yfield();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CcblineContext extends ParserRuleContext {
		public YfieldContext yfield() {
			return getRuleContext(YfieldContext.class,0);
		}
		public XfieldContext xfield() {
			return getRuleContext(XfieldContext.class,0);
		}
		public TerminalNode CCB() { return getToken(BoardParser.CCB, 0); }
		public NamefieldContext namefield() {
			return getRuleContext(NamefieldContext.class,0);
		}
		public CcblineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ccbline; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterCcbline(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitCcbline(this);
		}
	}

	public final CcblineContext ccbline() throws RecognitionException {
		CcblineContext _localctx = new CcblineContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_ccbline);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101); match(CCB);
			setState(102); namefield();
			setState(103); xfield();
			setState(104); yfield();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TriblineContext extends ParserRuleContext {
		public YfieldContext yfield() {
			return getRuleContext(YfieldContext.class,0);
		}
		public XfieldContext xfield() {
			return getRuleContext(XfieldContext.class,0);
		}
		public TerminalNode TRIB() { return getToken(BoardParser.TRIB, 0); }
		public OrtfieldContext ortfield() {
			return getRuleContext(OrtfieldContext.class,0);
		}
		public NamefieldContext namefield() {
			return getRuleContext(NamefieldContext.class,0);
		}
		public TriblineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tribline; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterTribline(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitTribline(this);
		}
	}

	public final TriblineContext tribline() throws RecognitionException {
		TriblineContext _localctx = new TriblineContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_tribline);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106); match(TRIB);
			setState(107); namefield();
			setState(108); xfield();
			setState(109); yfield();
			setState(110); ortfield();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LeftflineContext extends ParserRuleContext {
		public YfieldContext yfield() {
			return getRuleContext(YfieldContext.class,0);
		}
		public XfieldContext xfield() {
			return getRuleContext(XfieldContext.class,0);
		}
		public OrtfieldContext ortfield() {
			return getRuleContext(OrtfieldContext.class,0);
		}
		public NamefieldContext namefield() {
			return getRuleContext(NamefieldContext.class,0);
		}
		public TerminalNode LEFTF() { return getToken(BoardParser.LEFTF, 0); }
		public LeftflineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_leftfline; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterLeftfline(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitLeftfline(this);
		}
	}

	public final LeftflineContext leftfline() throws RecognitionException {
		LeftflineContext _localctx = new LeftflineContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_leftfline);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112); match(LEFTF);
			setState(113); namefield();
			setState(114); xfield();
			setState(115); yfield();
			setState(116); ortfield();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RightflineContext extends ParserRuleContext {
		public YfieldContext yfield() {
			return getRuleContext(YfieldContext.class,0);
		}
		public XfieldContext xfield() {
			return getRuleContext(XfieldContext.class,0);
		}
		public TerminalNode RIGHTF() { return getToken(BoardParser.RIGHTF, 0); }
		public OrtfieldContext ortfield() {
			return getRuleContext(OrtfieldContext.class,0);
		}
		public NamefieldContext namefield() {
			return getRuleContext(NamefieldContext.class,0);
		}
		public RightflineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rightfline; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterRightfline(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitRightfline(this);
		}
	}

	public final RightflineContext rightfline() throws RecognitionException {
		RightflineContext _localctx = new RightflineContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_rightfline);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118); match(RIGHTF);
			setState(119); namefield();
			setState(120); xfield();
			setState(121); yfield();
			setState(122); ortfield();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AbslineContext extends ParserRuleContext {
		public TerminalNode ABS() { return getToken(BoardParser.ABS, 0); }
		public YfieldContext yfield() {
			return getRuleContext(YfieldContext.class,0);
		}
		public WidthfieldContext widthfield() {
			return getRuleContext(WidthfieldContext.class,0);
		}
		public HeightfieldContext heightfield() {
			return getRuleContext(HeightfieldContext.class,0);
		}
		public XfieldContext xfield() {
			return getRuleContext(XfieldContext.class,0);
		}
		public NamefieldContext namefield() {
			return getRuleContext(NamefieldContext.class,0);
		}
		public AbslineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_absline; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterAbsline(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitAbsline(this);
		}
	}

	public final AbslineContext absline() throws RecognitionException {
		AbslineContext _localctx = new AbslineContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_absline);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124); match(ABS);
			setState(125); namefield();
			setState(126); xfield();
			setState(127); yfield();
			setState(128); widthfield();
			setState(129); heightfield();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FirelineContext extends ParserRuleContext {
		public TerminalNode FIRE() { return getToken(BoardParser.FIRE, 0); }
		public ActionfieldContext actionfield() {
			return getRuleContext(ActionfieldContext.class,0);
		}
		public TriggerfieldContext triggerfield() {
			return getRuleContext(TriggerfieldContext.class,0);
		}
		public FirelineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fireline; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterFireline(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitFireline(this);
		}
	}

	public final FirelineContext fireline() throws RecognitionException {
		FirelineContext _localctx = new FirelineContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_fireline);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131); match(FIRE);
			setState(132); triggerfield();
			setState(133); actionfield();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NamefieldContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(BoardParser.NAME, 0); }
		public TerminalNode NAMEL() { return getToken(BoardParser.NAMEL, 0); }
		public TerminalNode EQ() { return getToken(BoardParser.EQ, 0); }
		public NamefieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namefield; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterNamefield(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitNamefield(this);
		}
	}

	public final NamefieldContext namefield() throws RecognitionException {
		NamefieldContext _localctx = new NamefieldContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_namefield);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135); match(NAMEL);
			setState(136); match(EQ);
			setState(137); match(NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GravityfieldContext extends ParserRuleContext {
		public TerminalNode FLOAT() { return getToken(BoardParser.FLOAT, 0); }
		public TerminalNode EQ() { return getToken(BoardParser.EQ, 0); }
		public TerminalNode GRAVITY() { return getToken(BoardParser.GRAVITY, 0); }
		public GravityfieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gravityfield; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterGravityfield(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitGravityfield(this);
		}
	}

	public final GravityfieldContext gravityfield() throws RecognitionException {
		GravityfieldContext _localctx = new GravityfieldContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_gravityfield);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139); match(GRAVITY);
			setState(140); match(EQ);
			setState(141); match(FLOAT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Friction1fieldContext extends ParserRuleContext {
		public TerminalNode FLOAT() { return getToken(BoardParser.FLOAT, 0); }
		public TerminalNode EQ() { return getToken(BoardParser.EQ, 0); }
		public TerminalNode FRICTION1() { return getToken(BoardParser.FRICTION1, 0); }
		public Friction1fieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_friction1field; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterFriction1field(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitFriction1field(this);
		}
	}

	public final Friction1fieldContext friction1field() throws RecognitionException {
		Friction1fieldContext _localctx = new Friction1fieldContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_friction1field);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143); match(FRICTION1);
			setState(144); match(EQ);
			setState(145); match(FLOAT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Friction2fieldContext extends ParserRuleContext {
		public TerminalNode FRICTION2() { return getToken(BoardParser.FRICTION2, 0); }
		public TerminalNode FLOAT() { return getToken(BoardParser.FLOAT, 0); }
		public TerminalNode EQ() { return getToken(BoardParser.EQ, 0); }
		public Friction2fieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_friction2field; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterFriction2field(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitFriction2field(this);
		}
	}

	public final Friction2fieldContext friction2field() throws RecognitionException {
		Friction2fieldContext _localctx = new Friction2fieldContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_friction2field);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147); match(FRICTION2);
			setState(148); match(EQ);
			setState(149); match(FLOAT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class XffieldContext extends ParserRuleContext {
		public TerminalNode FLOAT() { return getToken(BoardParser.FLOAT, 0); }
		public TerminalNode EQ() { return getToken(BoardParser.EQ, 0); }
		public TerminalNode X() { return getToken(BoardParser.X, 0); }
		public XffieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_xffield; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterXffield(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitXffield(this);
		}
	}

	public final XffieldContext xffield() throws RecognitionException {
		XffieldContext _localctx = new XffieldContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_xffield);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151); match(X);
			setState(152); match(EQ);
			setState(153); match(FLOAT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class YffieldContext extends ParserRuleContext {
		public TerminalNode FLOAT() { return getToken(BoardParser.FLOAT, 0); }
		public TerminalNode EQ() { return getToken(BoardParser.EQ, 0); }
		public TerminalNode Y() { return getToken(BoardParser.Y, 0); }
		public YffieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_yffield; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterYffield(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitYffield(this);
		}
	}

	public final YffieldContext yffield() throws RecognitionException {
		YffieldContext _localctx = new YffieldContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_yffield);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155); match(Y);
			setState(156); match(EQ);
			setState(157); match(FLOAT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class XvfieldContext extends ParserRuleContext {
		public TerminalNode FLOAT() { return getToken(BoardParser.FLOAT, 0); }
		public TerminalNode XV() { return getToken(BoardParser.XV, 0); }
		public TerminalNode EQ() { return getToken(BoardParser.EQ, 0); }
		public XvfieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_xvfield; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterXvfield(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitXvfield(this);
		}
	}

	public final XvfieldContext xvfield() throws RecognitionException {
		XvfieldContext _localctx = new XvfieldContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_xvfield);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159); match(XV);
			setState(160); match(EQ);
			setState(161); match(FLOAT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class YvfieldContext extends ParserRuleContext {
		public TerminalNode FLOAT() { return getToken(BoardParser.FLOAT, 0); }
		public TerminalNode EQ() { return getToken(BoardParser.EQ, 0); }
		public TerminalNode YV() { return getToken(BoardParser.YV, 0); }
		public YvfieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_yvfield; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterYvfield(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitYvfield(this);
		}
	}

	public final YvfieldContext yvfield() throws RecognitionException {
		YvfieldContext _localctx = new YvfieldContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_yvfield);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163); match(YV);
			setState(164); match(EQ);
			setState(165); match(FLOAT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class XfieldContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(BoardParser.INT, 0); }
		public TerminalNode EQ() { return getToken(BoardParser.EQ, 0); }
		public TerminalNode X() { return getToken(BoardParser.X, 0); }
		public XfieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_xfield; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterXfield(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitXfield(this);
		}
	}

	public final XfieldContext xfield() throws RecognitionException {
		XfieldContext _localctx = new XfieldContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_xfield);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167); match(X);
			setState(168); match(EQ);
			setState(169); match(INT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class YfieldContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(BoardParser.INT, 0); }
		public TerminalNode EQ() { return getToken(BoardParser.EQ, 0); }
		public TerminalNode Y() { return getToken(BoardParser.Y, 0); }
		public YfieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_yfield; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterYfield(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitYfield(this);
		}
	}

	public final YfieldContext yfield() throws RecognitionException {
		YfieldContext _localctx = new YfieldContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_yfield);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171); match(Y);
			setState(172); match(EQ);
			setState(173); match(INT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OrtfieldContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(BoardParser.INT, 0); }
		public TerminalNode EQ() { return getToken(BoardParser.EQ, 0); }
		public TerminalNode ORT() { return getToken(BoardParser.ORT, 0); }
		public OrtfieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ortfield; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterOrtfield(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitOrtfield(this);
		}
	}

	public final OrtfieldContext ortfield() throws RecognitionException {
		OrtfieldContext _localctx = new OrtfieldContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_ortfield);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175); match(ORT);
			setState(176); match(EQ);
			setState(177); match(INT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WidthfieldContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(BoardParser.INT, 0); }
		public TerminalNode EQ() { return getToken(BoardParser.EQ, 0); }
		public TerminalNode WIDTH() { return getToken(BoardParser.WIDTH, 0); }
		public WidthfieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_widthfield; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterWidthfield(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitWidthfield(this);
		}
	}

	public final WidthfieldContext widthfield() throws RecognitionException {
		WidthfieldContext _localctx = new WidthfieldContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_widthfield);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(179); match(WIDTH);
			setState(180); match(EQ);
			setState(181); match(INT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HeightfieldContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(BoardParser.INT, 0); }
		public TerminalNode EQ() { return getToken(BoardParser.EQ, 0); }
		public TerminalNode HEIGHT() { return getToken(BoardParser.HEIGHT, 0); }
		public HeightfieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_heightfield; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterHeightfield(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitHeightfield(this);
		}
	}

	public final HeightfieldContext heightfield() throws RecognitionException {
		HeightfieldContext _localctx = new HeightfieldContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_heightfield);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183); match(HEIGHT);
			setState(184); match(EQ);
			setState(185); match(INT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TriggerfieldContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(BoardParser.NAME, 0); }
		public TerminalNode EQ() { return getToken(BoardParser.EQ, 0); }
		public TerminalNode TRIGGER() { return getToken(BoardParser.TRIGGER, 0); }
		public TriggerfieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_triggerfield; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterTriggerfield(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitTriggerfield(this);
		}
	}

	public final TriggerfieldContext triggerfield() throws RecognitionException {
		TriggerfieldContext _localctx = new TriggerfieldContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_triggerfield);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187); match(TRIGGER);
			setState(188); match(EQ);
			setState(189); match(NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ActionfieldContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(BoardParser.NAME, 0); }
		public TerminalNode EQ() { return getToken(BoardParser.EQ, 0); }
		public TerminalNode ACTION() { return getToken(BoardParser.ACTION, 0); }
		public ActionfieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_actionfield; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterActionfield(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitActionfield(this);
		}
	}

	public final ActionfieldContext actionfield() throws RecognitionException {
		ActionfieldContext _localctx = new ActionfieldContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_actionfield);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191); match(ACTION);
			setState(192); match(EQ);
			setState(193); match(NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 2: return lines_sempred((LinesContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean lines_sempred(LinesContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return 2 >= _localctx._p;
		}
		return true;
	}

	public static final String _serializedATN =
		"\2\3\36\u00c6\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b"+
		"\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t"+
		"\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t"+
		"\27\4\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\3\2\3\2\3\2\3\2"+
		"\3\3\3\3\3\3\5\3@\n\3\3\3\5\3C\n\3\3\3\5\3F\n\3\3\4\3\4\3\4\3\4\3\4\7"+
		"\4M\n\4\f\4\16\4P\13\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5Z\n\5\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17"+
		"\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\22\3\22\3\22"+
		"\3\22\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\26"+
		"\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\31\3\31\3\31"+
		"\3\31\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34"+
		"\2\35\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\66\2\2"+
		"\u00b5\28\3\2\2\2\4<\3\2\2\2\6G\3\2\2\2\bY\3\2\2\2\n[\3\2\2\2\fb\3\2\2"+
		"\2\16g\3\2\2\2\20l\3\2\2\2\22r\3\2\2\2\24x\3\2\2\2\26~\3\2\2\2\30\u0085"+
		"\3\2\2\2\32\u0089\3\2\2\2\34\u008d\3\2\2\2\36\u0091\3\2\2\2 \u0095\3\2"+
		"\2\2\"\u0099\3\2\2\2$\u009d\3\2\2\2&\u00a1\3\2\2\2(\u00a5\3\2\2\2*\u00a9"+
		"\3\2\2\2,\u00ad\3\2\2\2.\u00b1\3\2\2\2\60\u00b5\3\2\2\2\62\u00b9\3\2\2"+
		"\2\64\u00bd\3\2\2\2\66\u00c1\3\2\2\289\5\4\3\29:\5\6\4\2:;\7\1\2\2;\3"+
		"\3\2\2\2<=\7\3\2\2=?\5\32\16\2>@\5\34\17\2?>\3\2\2\2?@\3\2\2\2@B\3\2\2"+
		"\2AC\5\36\20\2BA\3\2\2\2BC\3\2\2\2CE\3\2\2\2DF\5 \21\2ED\3\2\2\2EF\3\2"+
		"\2\2F\5\3\2\2\2GH\b\4\1\2HI\5\b\5\2IN\3\2\2\2JK\6\4\2\3KM\5\b\5\2LJ\3"+
		"\2\2\2MP\3\2\2\2NL\3\2\2\2NO\3\2\2\2O\7\3\2\2\2PN\3\2\2\2QZ\5\n\6\2RZ"+
		"\5\f\7\2SZ\5\16\b\2TZ\5\20\t\2UZ\5\22\n\2VZ\5\24\13\2WZ\5\26\f\2XZ\5\30"+
		"\r\2YQ\3\2\2\2YR\3\2\2\2YS\3\2\2\2YT\3\2\2\2YU\3\2\2\2YV\3\2\2\2YW\3\2"+
		"\2\2YX\3\2\2\2Z\t\3\2\2\2[\\\7\22\2\2\\]\5\32\16\2]^\5\"\22\2^_\5$\23"+
		"\2_`\5&\24\2`a\5(\25\2a\13\3\2\2\2bc\7\23\2\2cd\5\32\16\2de\5*\26\2ef"+
		"\5,\27\2f\r\3\2\2\2gh\7\24\2\2hi\5\32\16\2ij\5*\26\2jk\5,\27\2k\17\3\2"+
		"\2\2lm\7\25\2\2mn\5\32\16\2no\5*\26\2op\5,\27\2pq\5.\30\2q\21\3\2\2\2"+
		"rs\7\26\2\2st\5\32\16\2tu\5*\26\2uv\5,\27\2vw\5.\30\2w\23\3\2\2\2xy\7"+
		"\27\2\2yz\5\32\16\2z{\5*\26\2{|\5,\27\2|}\5.\30\2}\25\3\2\2\2~\177\7\30"+
		"\2\2\177\u0080\5\32\16\2\u0080\u0081\5*\26\2\u0081\u0082\5,\27\2\u0082"+
		"\u0083\5\60\31\2\u0083\u0084\5\62\32\2\u0084\27\3\2\2\2\u0085\u0086\7"+
		"\31\2\2\u0086\u0087\5\64\33\2\u0087\u0088\5\66\34\2\u0088\31\3\2\2\2\u0089"+
		"\u008a\7\5\2\2\u008a\u008b\7\4\2\2\u008b\u008c\7\34\2\2\u008c\33\3\2\2"+
		"\2\u008d\u008e\7\6\2\2\u008e\u008f\7\4\2\2\u008f\u0090\7\33\2\2\u0090"+
		"\35\3\2\2\2\u0091\u0092\7\7\2\2\u0092\u0093\7\4\2\2\u0093\u0094\7\33\2"+
		"\2\u0094\37\3\2\2\2\u0095\u0096\7\b\2\2\u0096\u0097\7\4\2\2\u0097\u0098"+
		"\7\33\2\2\u0098!\3\2\2\2\u0099\u009a\7\13\2\2\u009a\u009b\7\4\2\2\u009b"+
		"\u009c\7\33\2\2\u009c#\3\2\2\2\u009d\u009e\7\f\2\2\u009e\u009f\7\4\2\2"+
		"\u009f\u00a0\7\33\2\2\u00a0%\3\2\2\2\u00a1\u00a2\7\t\2\2\u00a2\u00a3\7"+
		"\4\2\2\u00a3\u00a4\7\33\2\2\u00a4\'\3\2\2\2\u00a5\u00a6\7\n\2\2\u00a6"+
		"\u00a7\7\4\2\2\u00a7\u00a8\7\33\2\2\u00a8)\3\2\2\2\u00a9\u00aa\7\13\2"+
		"\2\u00aa\u00ab\7\4\2\2\u00ab\u00ac\7\32\2\2\u00ac+\3\2\2\2\u00ad\u00ae"+
		"\7\f\2\2\u00ae\u00af\7\4\2\2\u00af\u00b0\7\32\2\2\u00b0-\3\2\2\2\u00b1"+
		"\u00b2\7\r\2\2\u00b2\u00b3\7\4\2\2\u00b3\u00b4\7\32\2\2\u00b4/\3\2\2\2"+
		"\u00b5\u00b6\7\16\2\2\u00b6\u00b7\7\4\2\2\u00b7\u00b8\7\32\2\2\u00b8\61"+
		"\3\2\2\2\u00b9\u00ba\7\17\2\2\u00ba\u00bb\7\4\2\2\u00bb\u00bc\7\32\2\2"+
		"\u00bc\63\3\2\2\2\u00bd\u00be\7\20\2\2\u00be\u00bf\7\4\2\2\u00bf\u00c0"+
		"\7\34\2\2\u00c0\65\3\2\2\2\u00c1\u00c2\7\21\2\2\u00c2\u00c3\7\4\2\2\u00c3"+
		"\u00c4\7\34\2\2\u00c4\67\3\2\2\2\7?BENY";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}