import java.util.Hashtable;

public class DialogManager {

	private Hashtable<String, String> dialogs;


	public DialogManager(){
		dialogs = new Hashtable<String, String>();
	}

	public void loadLanguage(String name){
		dialogs = Languages.getLanguage(name);
	}

	public String getDialog(String id){
		if(dialogs.get(id) == null){
			return "???";
		}
		else{
			return dialogs.get(id);
		}
	}
}