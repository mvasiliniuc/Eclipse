package com.mobilesorcery.sdk.builder.blackberry.ui.preferences;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.mobilesorcery.sdk.builder.blackberry.BlackBerryPlugin;
import com.mobilesorcery.sdk.builder.java.KeystoreCertificateInfo;
import com.mobilesorcery.sdk.core.CoreMoSyncPlugin;
import com.mobilesorcery.sdk.ui.PasswordTextFieldDecorator;
import com.mobilesorcery.sdk.ui.UIUtils;

public class BlackBerrySigningPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {

	private Label passwordLabel;
	private Text password;
	private PasswordTextFieldDecorator passwordDec;

	public BlackBerrySigningPreferencePage() {
		super();
		setPreferenceStore(BlackBerryPlugin.getDefault().getPreferenceStore());
	}

	@Override
	public void init(IWorkbench workbench) {
	}

	@Override
	protected Control createContents(Composite parent) {
		Composite main = new Composite(parent, SWT.NONE);
		main.setLayout(UIUtils.newPrefsLayout(2));
        passwordLabel = new Label(main, SWT.PASSWORD);
        passwordLabel.setText("&Signature file password");
        
        password = new Text(main, SWT.BORDER | SWT.SINGLE);
        password.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        passwordDec = new PasswordTextFieldDecorator(password);

        init();
        return main;
	}
	
	private void init() {
		try {
			KeystoreCertificateInfo info = KeystoreCertificateInfo.parseOne(getPreferenceStore().getString(BlackBerryPlugin.BLACKBERRY_SIGNING_INFO));
			password.setText(info.getKeyPassword());
		} catch (Exception e) {
			// Ok, just ignore + clear info.
			CoreMoSyncPlugin.getDefault().log(e);
		}
	}
	
	@Override
	public boolean performOk() {
		KeystoreCertificateInfo info = new KeystoreCertificateInfo("", "", "", password.getText());
		getPreferenceStore().setValue(BlackBerryPlugin.BLACKBERRY_SIGNING_INFO, KeystoreCertificateInfo.unparse(info));
		return super.performOk();
	}

}