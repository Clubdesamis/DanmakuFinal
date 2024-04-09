import java.util.Hashtable;

public class Languages {

	private static Hashtable<String, Hashtable<String, String>> languages;
	private static Hashtable<String, String> frenchDialogs;
	private static Hashtable<String, String> englishDialogs;

	public static void init(){
		languages = new Hashtable<String, Hashtable<String, String>>();
		frenchDialogs = new Hashtable<String, String>();
		englishDialogs = new Hashtable<String, String>();
		loadFrench(frenchDialogs);
		loadEnglish(englishDialogs);
		languages.put("french", frenchDialogs);
		languages.put("english", englishDialogs);

	}

	public static Hashtable<String, String> getLanguage(String name){
		return languages.get(name);
	}


	private static void loadFrench(Hashtable<String, String> table){
		table.put("test_message", "ceci est un test");
		table.put("sign_in_label", "CONNEXION");
		table.put("username_label", "Nom d'utilisateur");
		table.put("password_label", "Mot de passe");
		table.put("sign_in_button_label", "Connexion");
		table.put("play_button_label", "Jouer");

	}

	private static void loadEnglish(Hashtable<String, String> table){
		table.put("test_message", "this is a test");
		table.put("sign_in_label", "SIGN IN");
		table.put("username_label", "Username");
		table.put("password_label", "Password");
		table.put("sign_in_button_label", "Sign In");
		table.put("play_button_label", "Play");
	}

}
