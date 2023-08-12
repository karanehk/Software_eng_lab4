package scanner;

import errorHandler.ErrorHandler;
import scanner.token.Token;
import scanner.type.Type;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class lexicalAnalyzer {
    private Matcher matcher;

    public lexicalAnalyzer(java.util.Scanner sc) {
        StringBuilder input = new StringBuilder();
        while (sc.hasNext()) {
            input.append(sc.nextLine());
        }
        StringBuilder tokenPattern = new StringBuilder();
        for (Type tokenType : Type.values())
            tokenPattern.append(String.format("|(?<%s>%s)", tokenType.name(), tokenType.pattern));
        Pattern expression = Pattern.compile(tokenPattern.substring(1));
        setMatcher(expression.matcher(input.toString()));
    }

    public Token getNextToken() {

        while (getMatcher().find()) {
            for (Type t : Type.values()) {

                if (getMatcher().group(t.name()) != null) {
                    if (getMatcher().group(Type.COMMENT.name()) != null) {
                        break;

                    }
                    if (getMatcher().group(Type.ErrorID.name()) != null) {
                        ErrorHandler.printError("The id must start with character");
                        break;
                    }

                    return new Token(t, getMatcher().group(t.name()));
                }
            }
        }
        return new Token(Type.EOF, "$");
    }

    public Matcher getMatcher() {
        return matcher;
    }

    public void setMatcher(Matcher matcher) {
        this.matcher = matcher;
    }

}
