/**
 * Derek Malikian
 * November 24, 2017
 * 
 * The purpose of the program is to write an algorithm that can perform 
 * AES encryption and decryption using Java.  Then use the AES algorithm
 * with a fixed key to encrypt the message and decrypt the message.
 * print out the encrypted and decrypted message
 */


import javax.crypto.Cipher;

// represents a factory for secret keys
// this object operates only on secret (symmetric) keys
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

public class dmalikian1PA4 {
	
	public static void main(String[] args) throws Exception {
		
		// create the message
		String plainText = "ProgrammingAssignment4";
		
		// Secret key object 
		SecretKey secKey = getSecretEncryptionKey();
		
		// pass the message and key to encrypt the message
		byte[] cipherText = encryptionAES(plainText, secKey);
		
		// pass the encrypted message and key to decrypte the message
		String decryptedText = decryptionAES(cipherText, secKey);
		
		// Print the original message 
		System.out.println("Original Text: " + plainText);
		
		// Print the key
		System.out.println("AES Key (Hex Form): " + bytesToHex(secKey.getEncoded()));
		
		// Print the encrypted text
		System.out.println("Encrypted Text (Hex Form); " + bytesToHex(cipherText));
		
		// Print the decrypted text i.e. original message
		System.out.println("Decrypted Text: " + decryptedText);
		

}

public static SecretKey getSecretEncryptionKey() throws Exception {
	
	// the key generator creates a symmetric key that can be used with that 
    // algorithm; a implementation instance is requested and instantiated by calling
	// the getInstance method and specifying the name of the desired algorithm
	// in this case AES
	
	KeyGenerator generator = KeyGenerator.getInstance("AES");
	
	// the generator shares the concept of a key-size and a source of randomness
	// the init method takes the key-size as the argument, and uses a system-provided
	// source of randomness
	
	generator.init(128);
	
	// convert data to a Secret Key object
	SecretKey secKey = generator.generateKey();
	
	// return the key
	return secKey;
}

public static byte[] encryptionAES(String plainText, SecretKey secKey) throws Exception {
	
	// the cipher class provides the functionality of a cryptographic cipher
	// used for encryption, this takes the data plain text and key and 
	// produces the cipher text.  the object is obtained with the transformation
	// AES 
	Cipher aesCipher = Cipher.getInstance("AES");
	
	// initialize the cipher object with the encryption of data mode
	aesCipher.init(Cipher.ENCRYPT_MODE,  secKey);
	
	// to encrypt the data in a single step, we call the doFinal method
	// the doFinal method will also take care of necessary padding
	byte[] byteCipherText = aesCipher.doFinal(plainText.getBytes());
	return byteCipherText;
}

public static String decryptionAES(byte[] byteCipherText, SecretKey secKey) throws Exception {
	
	// the cipher class provides the functionality of a cryptographic cipher 
	// used for decryption.
	// in this case decryption is the inverse process, taking cipher text and 
	// a key and producing clear text
	Cipher aesCipher = Cipher.getInstance("AES");
	
	// initialize the cipher object with the decryption data mode
	aesCipher.init(Cipher.DECRYPT_MODE,  secKey);
	
	// to decrypt the data in a single step, we call the doFinal method
	byte[] bytePlainText = aesCipher.doFinal(byteCipherText);
	
	// return the decrypted text
	return new String(bytePlainText);
}

// converts an array of bytes into a string
// @param array of bytes
// returns a string containing a lexical representation
// of xsd:hexBinary
private static String bytesToHex(byte[] hash) {
	
	// the DatatypeConverter class makes it easier 
	// to parse and print methods 
	return DatatypeConverter.printHexBinary(hash);
}

}

