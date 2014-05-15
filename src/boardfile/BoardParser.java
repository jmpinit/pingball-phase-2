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
		X=9, Y=10, ORT=11, WIDTH=12, HEIGHT=13, TRIGGER=14, ACTION=15, KEYLABEL=16, 
		OTHBOARDL=17, OTHPORTL=18, BALL=19, SQB=20, CCB=21, TRIB=22, LEFTF=23, 
		RIGHTF=24, ABS=25, FIRE=26, KEYL=27, PORTAL=28, INT=29, FLOAT=30, NAME=31, 
		KEY=32, WHITESPACE=33, COMMENT=34;
	public static final String[] tokenNames = {
		"<INVALID>", "'board'", "'='", "'name'", "'gravity'", "'friction1'", "'friction2'", 
		"'xVelocity'", "'yVelocity'", "'x'", "'y'", "'orientation'", "'width'", 
		"'height'", "'trigger'", "'action'", "'key'", "'otherBoard'", "'otherPortal'", 
		"'ball'", "'squareBumper'", "'circleBumper'", "'triangleBumper'", "'leftFlipper'", 
		"'rightFlipper'", "'absorber'", "'fire'", "KEYL", "'portal'", "INT", "FLOAT", 
		"NAME", "KEY", "WHITESPACE", "COMMENT"
	};
	public static final int
		RULE_board = 0, RULE_topline = 1, RULE_lines = 2, RULE_line = 3, RULE_ballline = 4, 
		RULE_sqbline = 5, RULE_ccbline = 6, RULE_tribline = 7, RULE_leftfline = 8, 
		RULE_rightfline = 9, RULE_absline = 10, RULE_fireline = 11, RULE_keyline = 12, 
		RULE_portalline = 13, RULE_namefield = 14, RULE_gravityfield = 15, RULE_friction1field = 16, 
		RULE_friction2field = 17, RULE_xffield = 18, RULE_yffield = 19, RULE_xvfield = 20, 
		RULE_yvfield = 21, RULE_xfield = 22, RULE_yfield = 23, RULE_ortfield = 24, 
		RULE_widthfield = 25, RULE_heightfield = 26, RULE_triggerfield = 27, RULE_actionfield = 28, 
		RULE_keyfield = 29, RULE_othboardfield = 30, RULE_othportfield = 31;
	public static final String[] ruleNames = {
		"board", "topline", "lines", "line", "ballline", "sqbline", "ccbline", 
		"tribline", "leftfline", "rightfline", "absline", "fireline", "keyline", 
		"portalline", "namefield", "gravityfield", "friction1field", "friction2field", 
		"xffield", "yffield", "xvfield", "yvfield", "xfield", "yfield", "ortfield", 
		"widthfield", "heightfield", "triggerfield", "actionfield", "keyfield", 
		"othboardfield", "othportfield"
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
			setState(64); topline();
			setState(65); lines(0);
			setState(66); match(EOF);
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
			setState(68); match(BOARDW);
			setState(69); namefield();
			setState(71);
			_la = _input.LA(1);
			if (_la==GRAVITY) {
				{
				setState(70); gravityfield();
				}
			}

			setState(74);
			_la = _input.LA(1);
			if (_la==FRICTION1) {
				{
				setState(73); friction1field();
				}
			}

			setState(77);
			_la = _input.LA(1);
			if (_la==FRICTION2) {
				{
				setState(76); friction2field();
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
			setState(80); line();
			}
			_ctx.stop = _input.LT(-1);
			setState(86);
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
					setState(82);
					if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
					setState(83); line();
					}
					} 
				}
				setState(88);
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
		public PortallineContext portalline() {
			return getRuleContext(PortallineContext.class,0);
		}
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
		public KeylineContext keyline() {
			return getRuleContext(KeylineContext.class,0);
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
			setState(99);
			switch (_input.LA(1)) {
			case BALL:
				enterOuterAlt(_localctx, 1);
				{
				setState(89); ballline();
				}
				break;
			case SQB:
				enterOuterAlt(_localctx, 2);
				{
				setState(90); sqbline();
				}
				break;
			case CCB:
				enterOuterAlt(_localctx, 3);
				{
				setState(91); ccbline();
				}
				break;
			case TRIB:
				enterOuterAlt(_localctx, 4);
				{
				setState(92); tribline();
				}
				break;
			case LEFTF:
				enterOuterAlt(_localctx, 5);
				{
				setState(93); leftfline();
				}
				break;
			case RIGHTF:
				enterOuterAlt(_localctx, 6);
				{
				setState(94); rightfline();
				}
				break;
			case ABS:
				enterOuterAlt(_localctx, 7);
				{
				setState(95); absline();
				}
				break;
			case FIRE:
				enterOuterAlt(_localctx, 8);
				{
				setState(96); fireline();
				}
				break;
			case KEYL:
				enterOuterAlt(_localctx, 9);
				{
				setState(97); keyline();
				}
				break;
			case PORTAL:
				enterOuterAlt(_localctx, 10);
				{
				setState(98); portalline();
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
			setState(101); match(BALL);
			setState(102); namefield();
			setState(103); xffield();
			setState(104); yffield();
			setState(105); xvfield();
			setState(106); yvfield();
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
			setState(108); match(SQB);
			setState(109); namefield();
			setState(110); xfield();
			setState(111); yfield();
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
			setState(113); match(CCB);
			setState(114); namefield();
			setState(115); xfield();
			setState(116); yfield();
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
			setState(118); match(TRIB);
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
			setState(124); match(LEFTF);
			setState(125); namefield();
			setState(126); xfield();
			setState(127); yfield();
			setState(128); ortfield();
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
			setState(130); match(RIGHTF);
			setState(131); namefield();
			setState(132); xfield();
			setState(133); yfield();
			setState(134); ortfield();
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
			setState(136); match(ABS);
			setState(137); namefield();
			setState(138); xfield();
			setState(139); yfield();
			setState(140); widthfield();
			setState(141); heightfield();
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
			setState(143); match(FIRE);
			setState(144); triggerfield();
			setState(145); actionfield();
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

	public static class KeylineContext extends ParserRuleContext {
		public KeyfieldContext keyfield() {
			return getRuleContext(KeyfieldContext.class,0);
		}
		public ActionfieldContext actionfield() {
			return getRuleContext(ActionfieldContext.class,0);
		}
		public TerminalNode KEYL() { return getToken(BoardParser.KEYL, 0); }
		public KeylineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyline; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterKeyline(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitKeyline(this);
		}
	}

	public final KeylineContext keyline() throws RecognitionException {
		KeylineContext _localctx = new KeylineContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_keyline);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147); match(KEYL);
			setState(148); actionfield();
			setState(149); keyfield();
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

	public static class PortallineContext extends ParserRuleContext {
		public YfieldContext yfield() {
			return getRuleContext(YfieldContext.class,0);
		}
		public OthportfieldContext othportfield() {
			return getRuleContext(OthportfieldContext.class,0);
		}
		public OthboardfieldContext othboardfield() {
			return getRuleContext(OthboardfieldContext.class,0);
		}
		public XfieldContext xfield() {
			return getRuleContext(XfieldContext.class,0);
		}
		public TerminalNode PORTAL() { return getToken(BoardParser.PORTAL, 0); }
		public NamefieldContext namefield() {
			return getRuleContext(NamefieldContext.class,0);
		}
		public PortallineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_portalline; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterPortalline(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitPortalline(this);
		}
	}

	public final PortallineContext portalline() throws RecognitionException {
		PortallineContext _localctx = new PortallineContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_portalline);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151); match(PORTAL);
			setState(152); namefield();
			setState(153); xfield();
			setState(154); yfield();
			setState(156);
			_la = _input.LA(1);
			if (_la==OTHBOARDL) {
				{
				setState(155); othboardfield();
				}
			}

			setState(158); othportfield();
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
		enterRule(_localctx, 28, RULE_namefield);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160); match(NAMEL);
			setState(161); match(EQ);
			setState(162); match(NAME);
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
		enterRule(_localctx, 30, RULE_gravityfield);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164); match(GRAVITY);
			setState(165); match(EQ);
			setState(166); match(FLOAT);
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
		enterRule(_localctx, 32, RULE_friction1field);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168); match(FRICTION1);
			setState(169); match(EQ);
			setState(170); match(FLOAT);
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
		enterRule(_localctx, 34, RULE_friction2field);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172); match(FRICTION2);
			setState(173); match(EQ);
			setState(174); match(FLOAT);
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
		enterRule(_localctx, 36, RULE_xffield);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176); match(X);
			setState(177); match(EQ);
			setState(178); match(FLOAT);
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
		enterRule(_localctx, 38, RULE_yffield);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180); match(Y);
			setState(181); match(EQ);
			setState(182); match(FLOAT);
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
		enterRule(_localctx, 40, RULE_xvfield);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(184); match(XV);
			setState(185); match(EQ);
			setState(186); match(FLOAT);
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
		enterRule(_localctx, 42, RULE_yvfield);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188); match(YV);
			setState(189); match(EQ);
			setState(190); match(FLOAT);
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
		enterRule(_localctx, 44, RULE_xfield);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(192); match(X);
			setState(193); match(EQ);
			setState(194); match(INT);
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
		enterRule(_localctx, 46, RULE_yfield);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(196); match(Y);
			setState(197); match(EQ);
			setState(198); match(INT);
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
		enterRule(_localctx, 48, RULE_ortfield);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(200); match(ORT);
			setState(201); match(EQ);
			setState(202); match(INT);
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
		enterRule(_localctx, 50, RULE_widthfield);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204); match(WIDTH);
			setState(205); match(EQ);
			setState(206); match(INT);
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
		enterRule(_localctx, 52, RULE_heightfield);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208); match(HEIGHT);
			setState(209); match(EQ);
			setState(210); match(INT);
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
		enterRule(_localctx, 54, RULE_triggerfield);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212); match(TRIGGER);
			setState(213); match(EQ);
			setState(214); match(NAME);
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
		enterRule(_localctx, 56, RULE_actionfield);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(216); match(ACTION);
			setState(217); match(EQ);
			setState(218); match(NAME);
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

	public static class KeyfieldContext extends ParserRuleContext {
		public TerminalNode KEYLABEL() { return getToken(BoardParser.KEYLABEL, 0); }
		public TerminalNode EQ() { return getToken(BoardParser.EQ, 0); }
		public TerminalNode KEY() { return getToken(BoardParser.KEY, 0); }
		public KeyfieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyfield; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterKeyfield(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitKeyfield(this);
		}
	}

	public final KeyfieldContext keyfield() throws RecognitionException {
		KeyfieldContext _localctx = new KeyfieldContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_keyfield);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(220); match(KEYLABEL);
			setState(221); match(EQ);
			setState(222); match(KEY);
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

	public static class OthboardfieldContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(BoardParser.NAME, 0); }
		public TerminalNode EQ() { return getToken(BoardParser.EQ, 0); }
		public TerminalNode OTHBOARDL() { return getToken(BoardParser.OTHBOARDL, 0); }
		public OthboardfieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_othboardfield; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterOthboardfield(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitOthboardfield(this);
		}
	}

	public final OthboardfieldContext othboardfield() throws RecognitionException {
		OthboardfieldContext _localctx = new OthboardfieldContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_othboardfield);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224); match(OTHBOARDL);
			setState(225); match(EQ);
			setState(226); match(NAME);
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

	public static class OthportfieldContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(BoardParser.NAME, 0); }
		public TerminalNode EQ() { return getToken(BoardParser.EQ, 0); }
		public TerminalNode OTHPORTL() { return getToken(BoardParser.OTHPORTL, 0); }
		public OthportfieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_othportfield; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).enterOthportfield(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BoardListener ) ((BoardListener)listener).exitOthportfield(this);
		}
	}

	public final OthportfieldContext othportfield() throws RecognitionException {
		OthportfieldContext _localctx = new OthportfieldContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_othportfield);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(228); match(OTHPORTL);
			setState(229); match(EQ);
			setState(230); match(NAME);
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
		"\2\3$\u00eb\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4"+
		"\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20"+
		"\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27"+
		"\4\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36"+
		"\4\37\t\37\4 \t \4!\t!\3\2\3\2\3\2\3\2\3\3\3\3\3\3\5\3J\n\3\3\3\5\3M\n"+
		"\3\3\3\5\3P\n\3\3\4\3\4\3\4\3\4\3\4\7\4W\n\4\f\4\16\4Z\13\4\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5f\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\5\17"+
		"\u009f\n\17\3\17\3\17\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\22\3\22"+
		"\3\22\3\22\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25"+
		"\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\31\3\31"+
		"\3\31\3\31\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34"+
		"\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3 \3 \3 "+
		"\3 \3!\3!\3!\3!\3!\2\"\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,"+
		".\60\62\64\668:<>@\2\2\u00d8\2B\3\2\2\2\4F\3\2\2\2\6Q\3\2\2\2\be\3\2\2"+
		"\2\ng\3\2\2\2\fn\3\2\2\2\16s\3\2\2\2\20x\3\2\2\2\22~\3\2\2\2\24\u0084"+
		"\3\2\2\2\26\u008a\3\2\2\2\30\u0091\3\2\2\2\32\u0095\3\2\2\2\34\u0099\3"+
		"\2\2\2\36\u00a2\3\2\2\2 \u00a6\3\2\2\2\"\u00aa\3\2\2\2$\u00ae\3\2\2\2"+
		"&\u00b2\3\2\2\2(\u00b6\3\2\2\2*\u00ba\3\2\2\2,\u00be\3\2\2\2.\u00c2\3"+
		"\2\2\2\60\u00c6\3\2\2\2\62\u00ca\3\2\2\2\64\u00ce\3\2\2\2\66\u00d2\3\2"+
		"\2\28\u00d6\3\2\2\2:\u00da\3\2\2\2<\u00de\3\2\2\2>\u00e2\3\2\2\2@\u00e6"+
		"\3\2\2\2BC\5\4\3\2CD\5\6\4\2DE\7\1\2\2E\3\3\2\2\2FG\7\3\2\2GI\5\36\20"+
		"\2HJ\5 \21\2IH\3\2\2\2IJ\3\2\2\2JL\3\2\2\2KM\5\"\22\2LK\3\2\2\2LM\3\2"+
		"\2\2MO\3\2\2\2NP\5$\23\2ON\3\2\2\2OP\3\2\2\2P\5\3\2\2\2QR\b\4\1\2RS\5"+
		"\b\5\2SX\3\2\2\2TU\6\4\2\3UW\5\b\5\2VT\3\2\2\2WZ\3\2\2\2XV\3\2\2\2XY\3"+
		"\2\2\2Y\7\3\2\2\2ZX\3\2\2\2[f\5\n\6\2\\f\5\f\7\2]f\5\16\b\2^f\5\20\t\2"+
		"_f\5\22\n\2`f\5\24\13\2af\5\26\f\2bf\5\30\r\2cf\5\32\16\2df\5\34\17\2"+
		"e[\3\2\2\2e\\\3\2\2\2e]\3\2\2\2e^\3\2\2\2e_\3\2\2\2e`\3\2\2\2ea\3\2\2"+
		"\2eb\3\2\2\2ec\3\2\2\2ed\3\2\2\2f\t\3\2\2\2gh\7\25\2\2hi\5\36\20\2ij\5"+
		"&\24\2jk\5(\25\2kl\5*\26\2lm\5,\27\2m\13\3\2\2\2no\7\26\2\2op\5\36\20"+
		"\2pq\5.\30\2qr\5\60\31\2r\r\3\2\2\2st\7\27\2\2tu\5\36\20\2uv\5.\30\2v"+
		"w\5\60\31\2w\17\3\2\2\2xy\7\30\2\2yz\5\36\20\2z{\5.\30\2{|\5\60\31\2|"+
		"}\5\62\32\2}\21\3\2\2\2~\177\7\31\2\2\177\u0080\5\36\20\2\u0080\u0081"+
		"\5.\30\2\u0081\u0082\5\60\31\2\u0082\u0083\5\62\32\2\u0083\23\3\2\2\2"+
		"\u0084\u0085\7\32\2\2\u0085\u0086\5\36\20\2\u0086\u0087\5.\30\2\u0087"+
		"\u0088\5\60\31\2\u0088\u0089\5\62\32\2\u0089\25\3\2\2\2\u008a\u008b\7"+
		"\33\2\2\u008b\u008c\5\36\20\2\u008c\u008d\5.\30\2\u008d\u008e\5\60\31"+
		"\2\u008e\u008f\5\64\33\2\u008f\u0090\5\66\34\2\u0090\27\3\2\2\2\u0091"+
		"\u0092\7\34\2\2\u0092\u0093\58\35\2\u0093\u0094\5:\36\2\u0094\31\3\2\2"+
		"\2\u0095\u0096\7\35\2\2\u0096\u0097\5:\36\2\u0097\u0098\5<\37\2\u0098"+
		"\33\3\2\2\2\u0099\u009a\7\36\2\2\u009a\u009b\5\36\20\2\u009b\u009c\5."+
		"\30\2\u009c\u009e\5\60\31\2\u009d\u009f\5> \2\u009e\u009d\3\2\2\2\u009e"+
		"\u009f\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a1\5@!\2\u00a1\35\3\2\2\2"+
		"\u00a2\u00a3\7\5\2\2\u00a3\u00a4\7\4\2\2\u00a4\u00a5\7!\2\2\u00a5\37\3"+
		"\2\2\2\u00a6\u00a7\7\6\2\2\u00a7\u00a8\7\4\2\2\u00a8\u00a9\7 \2\2\u00a9"+
		"!\3\2\2\2\u00aa\u00ab\7\7\2\2\u00ab\u00ac\7\4\2\2\u00ac\u00ad\7 \2\2\u00ad"+
		"#\3\2\2\2\u00ae\u00af\7\b\2\2\u00af\u00b0\7\4\2\2\u00b0\u00b1\7 \2\2\u00b1"+
		"%\3\2\2\2\u00b2\u00b3\7\13\2\2\u00b3\u00b4\7\4\2\2\u00b4\u00b5\7 \2\2"+
		"\u00b5\'\3\2\2\2\u00b6\u00b7\7\f\2\2\u00b7\u00b8\7\4\2\2\u00b8\u00b9\7"+
		" \2\2\u00b9)\3\2\2\2\u00ba\u00bb\7\t\2\2\u00bb\u00bc\7\4\2\2\u00bc\u00bd"+
		"\7 \2\2\u00bd+\3\2\2\2\u00be\u00bf\7\n\2\2\u00bf\u00c0\7\4\2\2\u00c0\u00c1"+
		"\7 \2\2\u00c1-\3\2\2\2\u00c2\u00c3\7\13\2\2\u00c3\u00c4\7\4\2\2\u00c4"+
		"\u00c5\7\37\2\2\u00c5/\3\2\2\2\u00c6\u00c7\7\f\2\2\u00c7\u00c8\7\4\2\2"+
		"\u00c8\u00c9\7\37\2\2\u00c9\61\3\2\2\2\u00ca\u00cb\7\r\2\2\u00cb\u00cc"+
		"\7\4\2\2\u00cc\u00cd\7\37\2\2\u00cd\63\3\2\2\2\u00ce\u00cf\7\16\2\2\u00cf"+
		"\u00d0\7\4\2\2\u00d0\u00d1\7\37\2\2\u00d1\65\3\2\2\2\u00d2\u00d3\7\17"+
		"\2\2\u00d3\u00d4\7\4\2\2\u00d4\u00d5\7\37\2\2\u00d5\67\3\2\2\2\u00d6\u00d7"+
		"\7\20\2\2\u00d7\u00d8\7\4\2\2\u00d8\u00d9\7!\2\2\u00d99\3\2\2\2\u00da"+
		"\u00db\7\21\2\2\u00db\u00dc\7\4\2\2\u00dc\u00dd\7!\2\2\u00dd;\3\2\2\2"+
		"\u00de\u00df\7\22\2\2\u00df\u00e0\7\4\2\2\u00e0\u00e1\7\"\2\2\u00e1=\3"+
		"\2\2\2\u00e2\u00e3\7\23\2\2\u00e3\u00e4\7\4\2\2\u00e4\u00e5\7!\2\2\u00e5"+
		"?\3\2\2\2\u00e6\u00e7\7\24\2\2\u00e7\u00e8\7\4\2\2\u00e8\u00e9\7!\2\2"+
		"\u00e9A\3\2\2\2\bILOXe\u009e";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	}
}