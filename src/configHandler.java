public class configHandler {

	public configHandler(){

	}

	public void loadConfig(){
		ScriptHeader binds = Game.scriptReader.readAllHeaders(Constants.CONFIG_FOLDER).get(0);
		Variables.UP_KEYCODE = binds.getAttribute(Constants.CONFIG_UP_KEY_TAG).charAt(0);
		Variables.DOWN_KEYCODE = binds.getAttribute(Constants.CONFIG_DOWN_KEY_TAG).charAt(0);
		Variables.LEFT_KEYCODE = binds.getAttribute(Constants.CONFIG_LEFT_KEY_TAG).charAt(0);
		Variables.RIGHT_KEYCODE = binds.getAttribute(Constants.CONFIG_RIGHT_KEY_TAG).charAt(0);
		Variables.SHOOT_KEYCODE = binds.getAttribute(Constants.CONFIG_SHOOT_KEY_TAG).charAt(0);
		Variables.LANGUAGE = binds.getAttribute(Constants.CONFIG_LANGUAGE_TAG);
	}

}
