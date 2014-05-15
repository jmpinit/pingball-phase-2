package boardfile;

import java.io.FileReader;
import java.io.IOException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;


public class Main {
    
    public static void main(String[] args) throws IOException {
        FileReader reader = new FileReader("boards/phase2features/allkeys.pb");
        CharStream stream = new ANTLRInputStream(reader);
       BoardLexer lexer = new BoardLexer(stream);
        lexer.reportErrorsAsExceptions();
        TokenStream tokens = new CommonTokenStream(lexer);
        
        // Feed the tokens into the parser.
        BoardParser parser = new BoardParser(tokens);
      //  parser.reportErrorsAsExceptions();
        
        ParseTree tree = parser.board();
        ((RuleContext) tree).inspect(parser);
        System.err.println(tree.toStringTree(parser));

    }

}
