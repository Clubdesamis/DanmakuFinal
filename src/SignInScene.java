import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class SignInScene implements Scene{

	private TextField userNameField;
	private TextField passwordField;

	private Label signInLabel;
	private Label userNameLabel;
	private Label passwordLabel;

	private Button signInButton;

	private ArrayList <VisualComponent> visualComponents;

	private Observable onClick;
	public SignInScene(){

	}

	public void init() throws IOException {
		visualComponents = new ArrayList<VisualComponent>();

		signInLabel = new Label(Game.dialogManager.getDialog("sign_in_label"), "sign_in", Color.WHITE);
		signInLabel.setPosition(75, 400);

		userNameLabel =  new Label(Game.dialogManager.getDialog("username_label"), "sign_in_label", Color.WHITE);
		userNameLabel.setPosition(75, 440);

		userNameField = new TextField();
		userNameField.setPosition(75, 470);
		userNameField.setSize(350, 25);
		userNameField.setFont("credentials");
		userNameField.setBackgroundColor(Color.white);
		userNameField.setTextColor(Color.white);
		userNameField.setMaxLength(20);

		passwordLabel = new Label(Game.dialogManager.getDialog("password_label"), "sign_in_label", Color.WHITE);
		passwordLabel.setPosition(75, 510);

		passwordField = new TextField();
		passwordField.setPosition(75, 540);
		passwordField.setSize(350, 25);
		passwordField.setFont("credentials");
		passwordField.setBackgroundColor(Color.WHITE);
		passwordField.setTextColor(Color.WHITE);
		passwordField.setMaxLength(20);
		passwordField.setIsPassword(true);

		signInButton = new Button(75, 580, 350, 25, Game.dialogManager.getDialog("sign_in_button_label"), new Color(14, 132, 168, 128), new Color(14, 132, 168, 255), Color.WHITE, "sign_in_label");
		signInButton.setTextPositionOffset(150, 18);

		visualComponents.add(userNameField);
		visualComponents.add(signInLabel);
		visualComponents.add(userNameLabel);
		visualComponents.add(passwordLabel);
		visualComponents.add(passwordField);
		visualComponents.add(signInButton);

		Game.observable.add(userNameField, Observable.EventID.MOUSE_CLICKED, Observable.EventID.KEY_TYPED);
		Game.observable.add(passwordField, Observable.EventID.MOUSE_CLICKED, Observable.EventID.KEY_TYPED);
		Game.observable.add(signInButton, Observable.EventID.MOUSE_CLICKED, Observable.EventID.MOUSE_MOVED);
	}

	@Override
	public void close() {

	}

	@Override
	public void render(Graphics graphics) {
		//graphics.drawString("Hello!", 100, 100);
		graphics.drawImage(Game.ressourceManager.getTexture("LoginBackground.png"), 0, 0, null);
		graphics.drawImage(Game.ressourceManager.getTexture("LoginShade.png"), 0, 0, null);
		for(int i = 0; i < visualComponents.size(); i++){
			visualComponents.get(i).draw(graphics);
		}
	}

	@Override
	public void simulate() {
		if(signInButton.isClicked()){
			signInButton.setClicked(false);
			try{
				Game.sceneManager.push("mainMenuScene");
			}
			catch(Exception e){}

		}
	}

	@Override
	public void setName(String name) {

	}

}