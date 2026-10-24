## Hash

- MD5 tilos
- SHA-1 tilos
- SHA-256-től ajánlott

## Password hash

- SHA-crypt
- PBKDF2
- bcrypt
- scrypt
- Argon2 család: Argon2d, Argon2i, Argon2id


## Cipher

- AES - blokkos - ajánlott
- IV (Initialization Vector): AES algoritmusnál egy véletlen, hogy ugyanannál a dokumentumnál más legyen a titkosított érték. Ezt is át kell küldeni.
- ChaCha20 — streamen - ajánlott
- RC4 - tilos
- Blowfish, Twofish régebbi
- DES - tilos
- 3DES - tilos

## Aláírás szimmetrikus kulccsal

- MAC

## Aszimmetrikus (nyilvános kulcsú) titkosítási algoritmus

- RSA - régebbi, elterjedt, egyelőre csak kvantumszámítógéppel törhető
- ECC
  - ECDSA és Ed25519/EdDSA digitális aláíráshoz (TLS-tanúsítványok, SSH, kriptovaluták)
  - ECDH és X25519 kulcscseréhez (a modern TLS 1.3 szinte mindig ezt használja)
- DSA
- Poszt-kvantum algoritmusok
  - ML-KEM
  - ML-DSA
  - SLH-DSA

## Sémaleírás

- ASN.1

## ASN.1 kódolás

- DER: bináris

## Szöveges kódolás

- PEM, BASE64

## Privát kulcs formátum

- PKCS#8

## Tanúsítvány szerkezet

- X.509

## Konténer formátum

Több privát kulcsot, publikus kulcsot vagy tanúsítványt tárolhatnak

- JKS (Java Key Store): Java specifikus, kerüljük
- PKCS#7 - tanúsítvány, tanúsítványlánc, de nem tartalmaz privát kulcsot, vagy publikus kulcsot magában
- PKCS#12 - privát kulcsot, publikus kulcsot vagy tanúsítványt tárolhatnak
