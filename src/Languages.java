import java.util.Hashtable;

public class Languages {

	private static Hashtable<String, Hashtable<String, String>> languages;
	private static Hashtable<String, String> frenchDialogs;
	private static Hashtable<String, String> englishDialogs;
	private static Hashtable<String, String> arabicDialogs;
	private static Hashtable<String, String> japaneseDialogs;

	public static void init(){
		languages = new Hashtable<String, Hashtable<String, String>>();
		frenchDialogs = new Hashtable<String, String>();
		englishDialogs = new Hashtable<String, String>();
		arabicDialogs = new Hashtable<String, String>();
		japaneseDialogs = new Hashtable<String, String>();
		loadFrench(frenchDialogs);
		loadEnglish(englishDialogs);
		loadArabic(arabicDialogs);
		loadJapanese(japaneseDialogs);
		languages.put("french", frenchDialogs);
		languages.put("english", englishDialogs);
		languages.put("arabic", arabicDialogs);
		languages.put("japanese", japaneseDialogs);

	}

	public static Hashtable<String, String> getLanguage(String name){
		return languages.get(name);
	}

	//table.put("", "");
	private static void loadFrench(Hashtable<String, String> table){
		table.put("test_message", "ceci est un test");
		table.put("sign_in_label", "CONNEXION");
		table.put("username_label", "Nom d'utilisateur");
		table.put("password_label", "Mot de passe");
		table.put("sign_in_button_label", "Connexion");
		table.put("play_button_label", "Jouer");
		table.put("score_indicator_label", "Score");
		table.put("map_cleared_label", "Niveau réussi");
		table.put("map_failed_label", "Niveau échoué");
		table.put("score_label", "Score:");
		table.put("continue_button", "Continuer");
		table.put("remaining_lives_label", "Vies restantes");
		table.put("sign_in_invalid_credentials", "Nom d'utilisateur ou mot de passe invalide");
		table.put("sign_in_server_error", "Connection au serveur impossible, veuillez réessayer plus tard");
	}

	private static void loadEnglish(Hashtable<String, String> table){
		table.put("test_message", "this is a test");
		table.put("sign_in_label", "SIGN IN");
		table.put("username_label", "Username");
		table.put("password_label", "Password");
		table.put("sign_in_button_label", "Sign In");
		table.put("play_button_label", "Play");
		table.put("score_indicator_label", "Score");
		table.put("map_cleared_label", "Map cleared");
		table.put("map_failed_label", "Map failed");
		table.put("score_label", "Score:");
		table.put("continue_button", "Continue");
		table.put("remaining_lives_label", "Remaining lives");
		table.put("sign_in_invalid_credentials", "Username or password is incorrect");
		table.put("sign_in_server_error", "Cannot connect to server, please try again later");
		table.put("current_user_label", "Currently signed in as ");
		table.put("sign_out_button", "Sign out");
		table.put("replay_button", "Replay");
	}

	private static void loadArabic(Hashtable<String, String> table){
		table.put("test_message", "هذا اختبار");
		table.put("sign_in_label", "تسجيل الدخول");
		table.put("username_label", "اسم المستخدم");
		table.put("password_label", "كلمة المرور");
		table.put("sign_in_button_label", "تسجيل الدخول");
		table.put("play_button_label", "يلعب");
		table.put("score_indicator_label", "نتيجة");
		table.put("map_cleared_label", "تم مسح الخريطة");
		table.put("map_failed_label", "فشلت الخريطة");
		table.put("score_label", "نتيجة");
		table.put("continue_button", "يكمل");
		table.put("remaining_lives_label", "الحياة المتبقية");
		table.put("sign_in_invalid_credentials", "اسم المستخدم أو كلمة المرور غير صحيحة");
		table.put("sign_in_server_error", "لا يمكن الاتصال بالخادم، يرجى المحاولة مرة أخرى لاحقًا");
	}

	private static void loadJapanese(Hashtable<String, String> table){
		table.put("test_message", "this is a test");
		table.put("sign_in_label", "サインイン");
		table.put("username_label", "ユーザー名");
		table.put("password_label", "パスワード");
		table.put("sign_in_button_label", "サインイン");
		table.put("play_button_label", "ゲームスタート");
		table.put("score_indicator_label", "スコア");
		table.put("map_cleared_label", "レベルクリア！");
		table.put("map_failed_label", "失敗！！");
		table.put("score_label", "スコア:");
		table.put("continue_button", "続ける");
		table.put("remaining_lives_label", "残りライフ");
		table.put("sign_in_invalid_credentials", "ユーザー名、又はパスワードが間違っています");
		table.put("sign_in_server_error", "サーバ接続に失敗しました、再度お試しください");
	}
}