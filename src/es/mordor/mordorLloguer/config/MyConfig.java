package es.mordor.mordorLloguer.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class MyConfig {
	private static MyConfig instancia = new MyConfig();

	private String dbFile = "db.properties";
	private String appFile = "app.properties";
	private Properties properties;

	private String key = "ieslavereda.es";
	private Map<String, String> propiedadesSeguras;

	private MyConfig() {

		Properties defaultProperties = new Properties();

		properties = new Properties(defaultProperties);

		try (FileInputStream fis = new FileInputStream(new File(dbFile))) {

			defaultProperties.load(fis);

		} catch (Exception e) {
			e.printStackTrace();
		}
		properties = new Properties(defaultProperties);
		try (FileInputStream fis = new FileInputStream(new File(appFile))) {

			properties.load(fis);

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			checkEncriptedProperties();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void checkEncriptedProperties() throws Exception {

		// Creamos un map con todas las propiedades que deben ser encriptadas,

		// junto con las propiedades que nos indican el estado de estas.

		propiedadesSeguras = new HashMap<String, String>();

		propiedadesSeguras.put("ORACLE_DB_USER", "IS_ORACLE_DB_USERNAME_ENCRYPTED");

		propiedadesSeguras.put("ORACLE_DB_PASSWORD", "IS_ORACLE_DB_PASSWORD_ENCRYPTED");

		// Si no existe la propiedad que indica si esta encriptado una key,

		// la creamos y la ponemos a false

		for (String isEncripted : propiedadesSeguras.values()) {

			if (!properties.containsKey(isEncripted))

				properties.put(isEncripted, "false");

		}

		// Encriptamos las claves si fuera necesario antes de leer las propiedades

		for (String property : propiedadesSeguras.keySet())

			encryptPropertyValue(property, propiedadesSeguras.get(property));

		// Guardamos las propiedades.

		// De esta forma las propiedades que no estaban encriptadas,

		// pasaran a disco encriptadas

		guardar();

	}

	private void encryptPropertyValue(String propertyKey, String isPropertyKeyEncrypted) {

		// Retrieve boolean properties value to see if password is already

		// encrypted or not

		String isEncrypted = properties.getProperty(isPropertyKeyEncrypted);

		// Check if password is encrypted?

		if (isEncrypted.equals("false")) {

			String tmpPwd = properties.getProperty(propertyKey);

			String encryptedPassword = encrypt(tmpPwd);

			// Overwrite password with encrypted password in the properties file

			// using Apache Commons Cinfiguration library

			properties.setProperty(propertyKey, encryptedPassword);

			// Set the boolean flag to true to indicate future encryption

			// operation that password is already encrypted

			properties.setProperty(isPropertyKeyEncrypted, "true");

			// Save the properties file

			guardar();

		}

	}

	private String encrypt(String tmpPwd) {

		// Encrypt

		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();

		encryptor.setPassword(key);

		String encryptedPassword = encryptor.encrypt(tmpPwd);

		return encryptedPassword;

	}

	private String decryptPropertyValue(String propertyKey) {

		String encryptedPropertyValue = properties.getProperty(propertyKey);

		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();

		encryptor.setPassword(key);

		String decryptedPropertyValue = encryptor.decrypt(encryptedPropertyValue);

		return decryptedPropertyValue;

	}

	public static MyConfig getInstancia() {
		return instancia;
	}

	public String getOracleDriver() {
		return properties.getProperty("ORACLE_DB_DRIVER");
	}

	public void setOracleDriver(String driver) {
		properties.setProperty("ORACLE_DB_DRIVER", driver);
		guardar();
	}

	public String getOracleURL() {
		return properties.getProperty("ORACLE_DB_URL");
	}

	public void setOracleURL(String url) {
		properties.setProperty("ORACLE_DB_URL", url);
		guardar();
	}

	public String getOracleUsername() {
		return decryptPropertyValue("ORACLE_DB_USER");
	}

	public void setOracleUsername(String user) {
		properties.setProperty("ORACLE_DB_USER", encrypt(user));
		properties.put("IS_ORACLE_DB_USERNAME_ENCRYPTED", "true");
		guardar();
	}

	public String getOraclePassword() {
		return decryptPropertyValue("ORACLE_DB_PASSWORD");
	}

	public void setOraclePassword(String password) {
		properties.setProperty("ORACLE_DB_PASSWORD", encrypt(password));
		properties.put("IS_ORACLE_DB_PASSWORD_ENCRYPTED", "true");
		guardar();
	}

	public void guardar() {

		try (FileOutputStream fos = new FileOutputStream(new File(appFile))) {

			properties.store(fos, "UTF-8");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}

