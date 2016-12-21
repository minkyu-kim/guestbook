function passHash(password) {
	var hashedValue = 1;
	for (var index=0; index<password.length; index++) {
		hashedValue *= 101;
		hashedValue += password.charCodeAt(index);
		hashedValue %= 1000000007;
	}
	for (var index=password.length-1; index>=0; index--) {
		hashedValue *= 101;
		hashedValue += password.charCodeAt(index);
		hashedValue %= 1000000007;
	}
	return hashedValue;
}