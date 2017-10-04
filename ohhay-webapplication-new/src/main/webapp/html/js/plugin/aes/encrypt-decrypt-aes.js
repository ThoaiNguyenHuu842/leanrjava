(function () {
	Aes={
			key1:'fjk393shs323fh2j',
			key2:'zxcmjasdhksahd33',
			encrypt:function(str){
				// mã hóa
				var key = CryptoJS.enc.Utf8.parse(this.key1);
				var iv  = CryptoJS.enc.Utf8.parse(this.key2);
				var encrypted = CryptoJS.AES.encrypt(str, key, { iv: iv });
				return encrypted;
			},
			decrypt:function(strEncrypt){
				
				// giải mã
				var key = CryptoJS.enc.Utf8.parse(this.key1);
				var iv  = CryptoJS.enc.Utf8.parse(this.key2);
				var decrypted = CryptoJS.AES.decrypt(strEncrypt, key, {iv:iv});
				
				return decrypted.toString(CryptoJS.enc.Utf8);
			},
	}
}());