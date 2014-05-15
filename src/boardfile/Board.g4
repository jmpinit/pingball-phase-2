grammar Board;

// This puts a Java package statement at the top of the output Java files.
@header {
package boardfile;
}

// This adds code to the generated lexer and parser.
@members {
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
}

/*
 * These are the lexical rules. They define the tokens used by the lexer.
 * *** ANTLR requires tokens to be CAPITALIZED, like START_ITALIC, END_ITALIC, and TEXT.
 */
 
BOARDW : 'board';
EQ : '=';

NAMEL : 'name';
GRAVITY : 'gravity';
FRICTION1 : 'friction1';
FRICTION2 : 'friction2';
XV : 'xVelocity';
YV : 'yVelocity';
X : 'x';
Y : 'y';
ORT : 'orientation';
WIDTH : 'width';
HEIGHT : 'height';
TRIGGER : 'trigger';
ACTION : 'action';
KEYLABEL: 'key';
OTHBOARDL : 'otherBoard';
OTHPORTL : 'otherPortal';

BALL : 'ball';
SQB : 'squareBumper';
CCB : 'circleBumper';
TRIB : 'triangleBumper';
LEFTF : 'leftFlipper';
RIGHTF : 'rightFlipper';
ABS : 'absorber';
FIRE : 'fire';
KEYL : 'keyup' | 'keydown';
PORTAL : 'portal';

INT : ('0'..'9')+;
FLOAT : ('-')?((('0'..'9')+'.'('0'..'9')*)|('.'?('0'..'9')+));
NAME : (('A'..'Z')|('a'..'z')|'_')(('A'..'Z')|('a'..'z')|'_'|'0'..'9')*;
KEY: [a-z] | [0-9]
        | 'shift' | 'ctrl' | 'alt' | 'meta'
        | 'space'
        | 'left' | 'right' | 'up' | 'down'
        | 'minus' | 'equals' | 'backspace'
        | 'openbracket' | 'closebracket' | 'backslash'
        | 'semicolon' | 'quote' | 'enter'
        | 'comma' | 'period' | 'slash' ;
        
WHITESPACE : [ \t\r\n]+ -> skip ;


COMMENT : '#' (~( '\r' | '\n' ))* -> skip ;

/*
 * These are the parser rules. They define the structures used by the parser.
 * *** ANTLR requires grammar nonterminals to be lowercase, like html, normal, and italic.
 */

 board : topline lines EOF;
 
 topline : BOARDW namefield (gravityfield)? (friction1field)? (friction2field)?;
 
 lines : lines line | line;
 line : ballline | sqbline | ccbline | tribline | leftfline | rightfline | absline | fireline | keyline | portalline;
 
 ballline : BALL namefield xffield yffield xvfield yvfield;
 sqbline : SQB namefield xfield yfield;
 ccbline : CCB namefield xfield yfield;
 tribline : TRIB namefield xfield yfield ortfield;
 leftfline : LEFTF namefield xfield yfield ortfield;
 rightfline : RIGHTF namefield xfield yfield ortfield;
 absline : ABS namefield xfield yfield widthfield heightfield;
 fireline : FIRE triggerfield actionfield;
 keyline: KEYL keyfield actionfield;
 portalline: PORTAL namefield xfield yfield (othboardfield)? othportfield;
 
 namefield : NAMEL EQ NAME;
 gravityfield : GRAVITY EQ FLOAT;
 friction1field : FRICTION1 EQ FLOAT;
 friction2field : FRICTION2 EQ FLOAT;
 
 xffield : X EQ FLOAT;
 yffield : Y EQ FLOAT;
 
 xvfield : XV EQ FLOAT;
 yvfield : YV EQ FLOAT;
 
 xfield : X EQ INT;
 yfield : Y EQ INT;
 
 ortfield : ORT EQ INT;
 widthfield : WIDTH EQ INT;
 heightfield : HEIGHT EQ INT;
 triggerfield : TRIGGER EQ NAME;
 actionfield : ACTION EQ NAME;
 keyfield : KEYLABEL EQ KEY;
 othboardfield : OTHBOARDL EQ NAME;
 othportfield : OTHPORTL EQ NAME;