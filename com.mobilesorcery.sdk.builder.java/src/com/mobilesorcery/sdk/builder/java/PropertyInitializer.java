/*  Copyright (C) 2009 Mobile Sorcery AB

    This program is free software; you can redistribute it and/or modify it
    under the terms of the Eclipse Public License v1.0.

    This program is distributed in the hope that it will be useful, but WITHOUT
    ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
    FITNESS FOR A PARTICULAR PURPOSE. See the Eclipse Public License v1.0 for
    more details.

    You should have received a copy of the Eclipse Public License v1.0 along
    with this program. It is also available at http://www.eclipse.org/legal/epl-v10.html
*/
package com.mobilesorcery.sdk.builder.java;

import java.util.ArrayList;
import java.util.Random;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import com.mobilesorcery.sdk.core.IPropertyInitializerDelegate;
import com.mobilesorcery.sdk.core.IPropertyOwner;
import com.mobilesorcery.sdk.core.PropertyUtil;

public class PropertyInitializer extends AbstractPreferenceInitializer implements IPropertyInitializerDelegate {

    private static final String PREFIX = "javame:"; //$NON-NLS-1$
    
    public static final String JAVAME_KEYSTORE_CERT_INFOS = PREFIX + "keystore.cert.infos"; //$NON-NLS-1$
    
    public static final String JAVAME_PROJECT_SPECIFIC_KEYS = PREFIX + "proj.spec.keys"; //$NON-NLS-1$

    private static Random rnd = new Random(System.currentTimeMillis());
    
    public PropertyInitializer() {
    }

    public String getDefaultValue(IPropertyOwner p, String key) {
        if (key.equals(JAVAME_PROJECT_SPECIFIC_KEYS)) {
            return PropertyUtil.fromBoolean(false);
        } else {
            return Activator.getDefault().getPreferenceStore().getString(key);
        }
    }

    public void initializeDefaultPreferences() {
        IPreferenceStore store = Activator.getDefault().getPreferenceStore();
        // By default, java me apps are NOT signed.
        store.setDefault(JAVAME_KEYSTORE_CERT_INFOS, KeystoreCertificateInfo.unparse(new ArrayList<KeystoreCertificateInfo>()));
    }


}
