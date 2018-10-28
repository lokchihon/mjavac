// Generated from c:\Users\Nicolas Van Kempen\Dropbox\Oswego\Fall 2018\CSC 444\mjavac\src\main\antlr4\com\nvankempen\csc444\mjava\MiniJavaLexer.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MiniJavaLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		CLASS=1, EXTENDS=2, RETURN=3, NEW=4, PUBLIC=5, STATIC=6, VOID=7, IF=8, 
		ELSE=9, WHILE=10, INT=11, BOOLEAN=12, VAR=13, LP=14, RP=15, LC=16, RC=17, 
		LB=18, RB=19, DOT=20, SEMICOLON=21, COMMA=22, EQ=23, AND=24, LT=25, PLUS=26, 
		MINUS=27, TIMES=28, NOT=29, MAIN=30, STRING=31, PRINT=32, LENGTH=33, THIS=34, 
		BOOLEAN_LITERAL=35, INTEGER_LITERAL=36, IDENTIFIER=37, WS=38, COMMENT=39;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"CLASS", "EXTENDS", "RETURN", "NEW", "PUBLIC", "STATIC", "VOID", "IF", 
		"ELSE", "WHILE", "INT", "BOOLEAN", "VAR", "LP", "RP", "LC", "RC", "LB", 
		"RB", "DOT", "SEMICOLON", "COMMA", "EQ", "AND", "LT", "PLUS", "MINUS", 
		"TIMES", "NOT", "MAIN", "STRING", "PRINT", "LENGTH", "THIS", "BOOLEAN_LITERAL", 
		"INTEGER_LITERAL", "IDENTIFIER", "WS", "COMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'class'", "'extends'", "'return'", "'new'", "'public'", "'static'", 
		"'void'", "'if'", "'else'", "'while'", "'int'", "'boolean'", "'var'", 
		"'('", "')'", "'{'", "'}'", "'['", "']'", "'.'", "';'", "','", "'='", 
		"'&&'", "'<'", "'+'", "'-'", "'*'", "'!'", "'main'", "'String'", "'System.out.println'", 
		"'length'", "'this'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "CLASS", "EXTENDS", "RETURN", "NEW", "PUBLIC", "STATIC", "VOID", 
		"IF", "ELSE", "WHILE", "INT", "BOOLEAN", "VAR", "LP", "RP", "LC", "RC", 
		"LB", "RB", "DOT", "SEMICOLON", "COMMA", "EQ", "AND", "LT", "PLUS", "MINUS", 
		"TIMES", "NOT", "MAIN", "STRING", "PRINT", "LENGTH", "THIS", "BOOLEAN_LITERAL", 
		"INTEGER_LITERAL", "IDENTIFIER", "WS", "COMMENT"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public MiniJavaLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MiniJavaLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2)\u0110\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3"+
		"\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b"+
		"\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16"+
		"\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25"+
		"\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\33\3\33"+
		"\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3"+
		" \3 \3 \3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3\"\3"+
		"\"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3$\3$\3$\3$\5$\u00f1"+
		"\n$\3%\6%\u00f4\n%\r%\16%\u00f5\3&\3&\7&\u00fa\n&\f&\16&\u00fd\13&\3\'"+
		"\6\'\u0100\n\'\r\'\16\'\u0101\3\'\3\'\3(\3(\3(\3(\7(\u010a\n(\f(\16(\u010d"+
		"\13(\3(\3(\2\2)\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31"+
		"\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65"+
		"\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)\3\2\7\3\2\62;\6\2&&C\\aac|\7\2"+
		"&&\62;C\\aac|\5\2\13\f\16\17\"\"\4\2\f\f\17\17\2\u0114\2\3\3\2\2\2\2\5"+
		"\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2"+
		"\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33"+
		"\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2"+
		"\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2"+
		"\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2"+
		"\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K"+
		"\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\3Q\3\2\2\2\5W\3\2\2\2\7_\3\2\2\2\tf\3\2"+
		"\2\2\13j\3\2\2\2\rq\3\2\2\2\17x\3\2\2\2\21}\3\2\2\2\23\u0080\3\2\2\2\25"+
		"\u0085\3\2\2\2\27\u008b\3\2\2\2\31\u008f\3\2\2\2\33\u0097\3\2\2\2\35\u009b"+
		"\3\2\2\2\37\u009d\3\2\2\2!\u009f\3\2\2\2#\u00a1\3\2\2\2%\u00a3\3\2\2\2"+
		"\'\u00a5\3\2\2\2)\u00a7\3\2\2\2+\u00a9\3\2\2\2-\u00ab\3\2\2\2/\u00ad\3"+
		"\2\2\2\61\u00af\3\2\2\2\63\u00b2\3\2\2\2\65\u00b4\3\2\2\2\67\u00b6\3\2"+
		"\2\29\u00b8\3\2\2\2;\u00ba\3\2\2\2=\u00bc\3\2\2\2?\u00c1\3\2\2\2A\u00c8"+
		"\3\2\2\2C\u00db\3\2\2\2E\u00e2\3\2\2\2G\u00f0\3\2\2\2I\u00f3\3\2\2\2K"+
		"\u00f7\3\2\2\2M\u00ff\3\2\2\2O\u0105\3\2\2\2QR\7e\2\2RS\7n\2\2ST\7c\2"+
		"\2TU\7u\2\2UV\7u\2\2V\4\3\2\2\2WX\7g\2\2XY\7z\2\2YZ\7v\2\2Z[\7g\2\2[\\"+
		"\7p\2\2\\]\7f\2\2]^\7u\2\2^\6\3\2\2\2_`\7t\2\2`a\7g\2\2ab\7v\2\2bc\7w"+
		"\2\2cd\7t\2\2de\7p\2\2e\b\3\2\2\2fg\7p\2\2gh\7g\2\2hi\7y\2\2i\n\3\2\2"+
		"\2jk\7r\2\2kl\7w\2\2lm\7d\2\2mn\7n\2\2no\7k\2\2op\7e\2\2p\f\3\2\2\2qr"+
		"\7u\2\2rs\7v\2\2st\7c\2\2tu\7v\2\2uv\7k\2\2vw\7e\2\2w\16\3\2\2\2xy\7x"+
		"\2\2yz\7q\2\2z{\7k\2\2{|\7f\2\2|\20\3\2\2\2}~\7k\2\2~\177\7h\2\2\177\22"+
		"\3\2\2\2\u0080\u0081\7g\2\2\u0081\u0082\7n\2\2\u0082\u0083\7u\2\2\u0083"+
		"\u0084\7g\2\2\u0084\24\3\2\2\2\u0085\u0086\7y\2\2\u0086\u0087\7j\2\2\u0087"+
		"\u0088\7k\2\2\u0088\u0089\7n\2\2\u0089\u008a\7g\2\2\u008a\26\3\2\2\2\u008b"+
		"\u008c\7k\2\2\u008c\u008d\7p\2\2\u008d\u008e\7v\2\2\u008e\30\3\2\2\2\u008f"+
		"\u0090\7d\2\2\u0090\u0091\7q\2\2\u0091\u0092\7q\2\2\u0092\u0093\7n\2\2"+
		"\u0093\u0094\7g\2\2\u0094\u0095\7c\2\2\u0095\u0096\7p\2\2\u0096\32\3\2"+
		"\2\2\u0097\u0098\7x\2\2\u0098\u0099\7c\2\2\u0099\u009a\7t\2\2\u009a\34"+
		"\3\2\2\2\u009b\u009c\7*\2\2\u009c\36\3\2\2\2\u009d\u009e\7+\2\2\u009e"+
		" \3\2\2\2\u009f\u00a0\7}\2\2\u00a0\"\3\2\2\2\u00a1\u00a2\7\177\2\2\u00a2"+
		"$\3\2\2\2\u00a3\u00a4\7]\2\2\u00a4&\3\2\2\2\u00a5\u00a6\7_\2\2\u00a6("+
		"\3\2\2\2\u00a7\u00a8\7\60\2\2\u00a8*\3\2\2\2\u00a9\u00aa\7=\2\2\u00aa"+
		",\3\2\2\2\u00ab\u00ac\7.\2\2\u00ac.\3\2\2\2\u00ad\u00ae\7?\2\2\u00ae\60"+
		"\3\2\2\2\u00af\u00b0\7(\2\2\u00b0\u00b1\7(\2\2\u00b1\62\3\2\2\2\u00b2"+
		"\u00b3\7>\2\2\u00b3\64\3\2\2\2\u00b4\u00b5\7-\2\2\u00b5\66\3\2\2\2\u00b6"+
		"\u00b7\7/\2\2\u00b78\3\2\2\2\u00b8\u00b9\7,\2\2\u00b9:\3\2\2\2\u00ba\u00bb"+
		"\7#\2\2\u00bb<\3\2\2\2\u00bc\u00bd\7o\2\2\u00bd\u00be\7c\2\2\u00be\u00bf"+
		"\7k\2\2\u00bf\u00c0\7p\2\2\u00c0>\3\2\2\2\u00c1\u00c2\7U\2\2\u00c2\u00c3"+
		"\7v\2\2\u00c3\u00c4\7t\2\2\u00c4\u00c5\7k\2\2\u00c5\u00c6\7p\2\2\u00c6"+
		"\u00c7\7i\2\2\u00c7@\3\2\2\2\u00c8\u00c9\7U\2\2\u00c9\u00ca\7{\2\2\u00ca"+
		"\u00cb\7u\2\2\u00cb\u00cc\7v\2\2\u00cc\u00cd\7g\2\2\u00cd\u00ce\7o\2\2"+
		"\u00ce\u00cf\7\60\2\2\u00cf\u00d0\7q\2\2\u00d0\u00d1\7w\2\2\u00d1\u00d2"+
		"\7v\2\2\u00d2\u00d3\7\60\2\2\u00d3\u00d4\7r\2\2\u00d4\u00d5\7t\2\2\u00d5"+
		"\u00d6\7k\2\2\u00d6\u00d7\7p\2\2\u00d7\u00d8\7v\2\2\u00d8\u00d9\7n\2\2"+
		"\u00d9\u00da\7p\2\2\u00daB\3\2\2\2\u00db\u00dc\7n\2\2\u00dc\u00dd\7g\2"+
		"\2\u00dd\u00de\7p\2\2\u00de\u00df\7i\2\2\u00df\u00e0\7v\2\2\u00e0\u00e1"+
		"\7j\2\2\u00e1D\3\2\2\2\u00e2\u00e3\7v\2\2\u00e3\u00e4\7j\2\2\u00e4\u00e5"+
		"\7k\2\2\u00e5\u00e6\7u\2\2\u00e6F\3\2\2\2\u00e7\u00e8\7v\2\2\u00e8\u00e9"+
		"\7t\2\2\u00e9\u00ea\7w\2\2\u00ea\u00f1\7g\2\2\u00eb\u00ec\7h\2\2\u00ec"+
		"\u00ed\7c\2\2\u00ed\u00ee\7n\2\2\u00ee\u00ef\7u\2\2\u00ef\u00f1\7g\2\2"+
		"\u00f0\u00e7\3\2\2\2\u00f0\u00eb\3\2\2\2\u00f1H\3\2\2\2\u00f2\u00f4\t"+
		"\2\2\2\u00f3\u00f2\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f5"+
		"\u00f6\3\2\2\2\u00f6J\3\2\2\2\u00f7\u00fb\t\3\2\2\u00f8\u00fa\t\4\2\2"+
		"\u00f9\u00f8\3\2\2\2\u00fa\u00fd\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fb\u00fc"+
		"\3\2\2\2\u00fcL\3\2\2\2\u00fd\u00fb\3\2\2\2\u00fe\u0100\t\5\2\2\u00ff"+
		"\u00fe\3\2\2\2\u0100\u0101\3\2\2\2\u0101\u00ff\3\2\2\2\u0101\u0102\3\2"+
		"\2\2\u0102\u0103\3\2\2\2\u0103\u0104\b\'\2\2\u0104N\3\2\2\2\u0105\u0106"+
		"\7\61\2\2\u0106\u0107\7\61\2\2\u0107\u010b\3\2\2\2\u0108\u010a\n\6\2\2"+
		"\u0109\u0108\3\2\2\2\u010a\u010d\3\2\2\2\u010b\u0109\3\2\2\2\u010b\u010c"+
		"\3\2\2\2\u010c\u010e\3\2\2\2\u010d\u010b\3\2\2\2\u010e\u010f\b(\2\2\u010f"+
		"P\3\2\2\2\b\2\u00f0\u00f5\u00fb\u0101\u010b\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}