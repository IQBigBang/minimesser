// Generated from C:/Users/42060/IdeaProjects/minimesser-kotlin/src/main/kotlin\Minimesser.g4 by ANTLR 4.9.1
package parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MinimesserLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, ID=14, WS=15;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "ID", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'message'", "'{'", "'}'", "':'", "';'", "'int8'", "'int16'", "'int32'", 
			"'int64'", "'uint8'", "'uint16'", "'uint32'", "'uint64'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "ID", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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


	public MinimesserLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Minimesser.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\21q\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\7\17f\n\17\f"+
		"\17\16\17i\13\17\3\20\6\20l\n\20\r\20\16\20m\3\20\3\20\2\2\21\3\3\5\4"+
		"\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21\3"+
		"\2\5\4\2C\\c|\7\2//\62;C\\aac|\5\2\13\f\17\17\"\"\2r\2\3\3\2\2\2\2\5\3"+
		"\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2"+
		"\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3"+
		"\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\3!\3\2\2\2\5)\3\2\2\2\7+\3\2\2\2\t-\3"+
		"\2\2\2\13/\3\2\2\2\r\61\3\2\2\2\17\66\3\2\2\2\21<\3\2\2\2\23B\3\2\2\2"+
		"\25H\3\2\2\2\27N\3\2\2\2\31U\3\2\2\2\33\\\3\2\2\2\35c\3\2\2\2\37k\3\2"+
		"\2\2!\"\7o\2\2\"#\7g\2\2#$\7u\2\2$%\7u\2\2%&\7c\2\2&\'\7i\2\2\'(\7g\2"+
		"\2(\4\3\2\2\2)*\7}\2\2*\6\3\2\2\2+,\7\177\2\2,\b\3\2\2\2-.\7<\2\2.\n\3"+
		"\2\2\2/\60\7=\2\2\60\f\3\2\2\2\61\62\7k\2\2\62\63\7p\2\2\63\64\7v\2\2"+
		"\64\65\7:\2\2\65\16\3\2\2\2\66\67\7k\2\2\678\7p\2\289\7v\2\29:\7\63\2"+
		"\2:;\78\2\2;\20\3\2\2\2<=\7k\2\2=>\7p\2\2>?\7v\2\2?@\7\65\2\2@A\7\64\2"+
		"\2A\22\3\2\2\2BC\7k\2\2CD\7p\2\2DE\7v\2\2EF\78\2\2FG\7\66\2\2G\24\3\2"+
		"\2\2HI\7w\2\2IJ\7k\2\2JK\7p\2\2KL\7v\2\2LM\7:\2\2M\26\3\2\2\2NO\7w\2\2"+
		"OP\7k\2\2PQ\7p\2\2QR\7v\2\2RS\7\63\2\2ST\78\2\2T\30\3\2\2\2UV\7w\2\2V"+
		"W\7k\2\2WX\7p\2\2XY\7v\2\2YZ\7\65\2\2Z[\7\64\2\2[\32\3\2\2\2\\]\7w\2\2"+
		"]^\7k\2\2^_\7p\2\2_`\7v\2\2`a\78\2\2ab\7\66\2\2b\34\3\2\2\2cg\t\2\2\2"+
		"df\t\3\2\2ed\3\2\2\2fi\3\2\2\2ge\3\2\2\2gh\3\2\2\2h\36\3\2\2\2ig\3\2\2"+
		"\2jl\t\4\2\2kj\3\2\2\2lm\3\2\2\2mk\3\2\2\2mn\3\2\2\2no\3\2\2\2op\b\20"+
		"\2\2p \3\2\2\2\5\2gm\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}