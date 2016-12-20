function hash(password) {
	var hashedValue = 1;
	for ( var character in password) {
		hashedValue *= 101;
		hashedValue += character;
		hashedValue %= 1000000007;
	}
	return hashedValue;
}