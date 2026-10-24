# OWASP Top 10

## CWE

- A Common Weakness Enumeration a szoftveres és hardveres gyengeségtípusok szabványosított, közösségi fejlesztésű katalógusa
- MITRE nevű non-profit szervezet gondoz
- Minden bejegyzés egy általános hibatípust ír le, saját azonosítóval
- [CWE-79](https://cwe.mitre.org/data/definitions/79.html) a cross-site scripting (XSS)
- [CWE-89](https://cwe.mitre.org/data/definitions/89.html) pedig az SQL-injection
- Ezek nem konkrét szoftver verzióban lévő konkrét hibák, hanem hibaminták
- 944 db
- Nem mindegyik releváns minden programozási nyelvre vagy keretrendszerre
- Ebből is van [Top 25](we.mitre.org/top25/archive/2025/2025_cwe_top25.html)

## CVE

- Common Vulnerabilities and Exposures egy nyilvánosan ismert, konkrét biztonsági sebezhetőség egyedi azonosítója
- CVE minimális: azonosító, rövid leírás, hivatkozások
- Formátuma `CVE-ÉV-SZÁM`, például a Log4Shell néven ismert hiba a CVE-2021-44228
- CVE-program egy névadó és koordináló rendszer, szintén a MITRE üzemelteti, CISA támogatásával
    - CISA (Cybersecurity and Infrastructure Security Agency) az Egyesült Államok Belbiztonsági Minisztériumához (DHS) tartozó ügynökség

## NVD

- NVD (National Vulnerability Database) tárolja, elemzi, pontozza
- NIST vezeti
    - A NIST (National Institute of Standards and Technology) az amerikai Kereskedelmi Minisztérium alá tartozó intézet
- Súlyosságot kifejező pontszám: CVSS-pontszám

## CVE és NVD kapcsolata

- A névadásnak gyorsnak és semlegesnek kell lennie
- A pontozás és a termékleképezés viszont időigényes
- A határ az utóbbi években egyébként elmosódott

## OWASP Top 10

* Webalkalmazásokkal kapcsolatos leggyakoribb biztonsági problémákat listázza
* [OWASP Top 10:2025](https://top10.owasp.org/2025/) legfrissebb verzió
* Tudatosságnövelő dokumentum
* Bevezető lépés a biztonságos kódolás felé

## Módszertan

- Nonprofit szervezet
- Adatgyűjtés különböző szervezetektől, biztonsági cégektől, kutatóktól
- Webes alkalmazások tesztelése során mennyinél találtak legalább egy példányt egy adott CWE-ből
- A CWE-ket kategóriákba sorolják, egy adott kategóriához tartozó CWE-k elérhetőek a List of Mapped CWEs részben
- Súlyozzák gyakoriság, kihasználhatóság egyszerűsége és hatás alapján, figyelembe véve a CVSS pontszámot
- Bizonyos időközönként újraértékelik

## OWASP Top 10:2025 kategóriák

- A01:2025 Broken Access Control
- A02:2025 Security Misconfiguration
- A03:2025 Software Supply Chain Failures
- A04:2025 Cryptographic Failures
- A05:2025 Injection
- A06:2025 Insecure Design
- A07:2025 Authentication Failures
- A08:2025 Software or Data Integrity Failures
- A09:2025 Logging and Alerting Failures
- A10:2025 Mishandling of Exceptional Conditions

# Alapfogalmak

## Information security - CIA

- Confidentiality - bizalmasság: az információ csak az arra felhatalmazottak számára legyen elérhető
- Integrity - sértetlenség: információk és a feldolgozási módszerek teljessége és helyessége
- Availability - rendelkezésre állás: felhasználók mindig hozzáférjenek az információkhoz

# Algoritmusok és kriptográfiai fogalmak

## nonce

- "number used once"
- Olyan érték, amelyet egy adott művelethez vagy üzenethez egyszer használnak fel, és soha többet
- Lehet számláló vagy véletlenszám

## BASE64

- Kódolási eljárás, amely tetszőleges bináris adatot (például képet, fájlt vagy bájtsorozatot) olvasható szöveggé alakít át, mindössze 64 különböző, biztonságosan továbbítható ASCII karakter használatával
- Bármikor visszafejthető, kétirányú
- Az eredmény nagyjából 33%-kal nagyobb

## Hash

- Tetszőleges hosszúságú bemenetből fix hosszúságú "ujjlenyomatot" (hash-t vagy kivonatot) számol
- Ugyanabból a bemenetből mindig ugyanaz az eredmény lesz
- Egyirányú, nem lehet kitalálni a bemenetet
- Bemenet legkisebb változtatására is teljesen más hash jön ki (lavinahatás)
- Lehetetlen vele megegyező hash-ű értelmes dokumentumot legyártani (ütközésmentesség)
- Gyorsan kiszámolható

Mire használják?

- Integritásellenőrzés: nem-e módosult a dokumentum
- Jelszótárolás
- Digitális aláírás és tanúsítványok: nem a teljes dokumentumot írják alá, hanem a hash-ét, ami gyorsabb
- Adatszerkezetek
- Git
- Blokklánc

Algoritmusok:

- MD5 tilos
- SHA-1 tilos
- SHA-256-től ajánlott

Jelszavakhoz nem használható, mert túl gyors, így a találgatásos támadás (brute force) is gyors.

## Jelszó tárolás

- Nem elég az egyszerű hash, mert gyorsan számolható
- Lassítás: nagyon sokszor elvégezni egymás után
- Nem jó, ha a hash determinisztikus, rainbow table támadással a hash alapján előkereshető a jelszó
    - Nem tárolja az összes jelszó - hash párost, mert az óriási tárterületet foglalna
- Salt: így ugyanaz a jelszó mindig más hash-t ad
- Pepper: alkalmazásonkénti megkülönböztető érték
- Side channel támadás: megvalósítás fizikai vagy időbeli mellékhatásait
    - Időzítéses (timing): a műveletek időtartamából következtet
    - Cache-alapú: a CPU gyorsítótárának állapotát figyeli
    - Spekulatív végrehajtás: a Spectre és a Meltdown a processzor előrejelző mechanizmusát használta ki, hogy védett memóriából szivárogtasson ki adatot
    - Teljesítményanalízis
    - Elektromágneses és akusztikus
    - Hibainjektálás: ez már aktív támadás, a feszültség vagy az órajel megzavarásával idéznek elő számítási hibát, és a hibás kimenetből számolják ki a kulcsot
- GPU-k fejlődése, egyszerre nagyon sok párhuzamos művelet
- Megoldás: memóriaigényes legyen

Algoritmusok:

- SHA-crypt
- PBKDF2
- bcrypt
- scrypt
- yescrypt - főleg Linux rendszerjelszavakhoz
- Argon2 család: Argon2d, Argon2i, Argon2id - alkalmazásokban

## Random generator

- Pseudo-Random Number Generators (PRNG)
- Cloudflare - lava lamp pool, [Lavarand](https://en.wikipedia.org/wiki/Lavarand)
- OS
    - Linux: a kernel ChaCha20-alapú generátort használ, amelyet hardveres zajforrásokból (megszakítások időzítése, RDRAND/RDSEED, jitter entropy) táplál.
    - Windows: CNG (Cryptography API: Next Generation), azaz BCryptGenRandom (függvény neve), amely NIST SP 800-90A (amerikai szabvány) szerinti CTR_DRBG-alapú (Deterministic Random Bit Generator), működése
        - Seed: a rendszer valódi entrópiát gyűjt (hardveres zaj, időzítések, CPU-beli RDRAND/RDSEED stb.), és ebből inicializálja a generátor belső állapotát, ami egy AES-kulcs és egy számláló.
        - Véletlenszám generálás: számláló növelése, kulcs, állapot frissítése
        - Reseed: időnként friss entrópiát kap

## Cipher

- Titkosság (privacy, confidentiality): a kommunikáló feleken kívül más nem szerezhet tudomást a dokumentum tartalmáról
- Szimmetrikus titkosítás
- Security by Obscurity kerülendő: algoritmus nem titkos

Algoritmusok:

- RC4 - tilos
- Blowfish, Twofish régebbi
- DES - tilos
- 3DES - tilos
- AES - blokkos - ajánlott
- ChaCha20 — streamen - ajánlott

## Szimmetrikus aláírás

Elektronikus aláírás:

- Hitelesség (authenticity): a dokumentum származásának igazolása
- Sérthetetlenség, integritás (integrity): a dokumentum tartalma nem változott annak aláírása óta
- Letagadhatatlanság (non-repudiation): az aláíró nem tudja letagadni, hogy ő írta alá a dokumentumot, és az aláíró személy kiléte jogilag is bizonyítható erejű

Algoritmus:

- MAC

## Aszimmetrikus (nyilvános kulcsú) titkosítási algoritmus

RSA

Kulcsok:

- A titkos (privát) kulcsot csak te ismered, soha nem hagyja el a gépedet vagy az eszközödet
- A nyilvános (publikus) kulcsot bárkinek odaadhatod

A biztonsága azon alapul, hogy két nagy prímszámot összeszorozni könnyű, de a szorzatból visszafejteni a tényezőket (faktorizáció) gyakorlatilag kivitelezhetetlen, ha a szám elég nagy.

Használata:

- Titkosítás: valaki a te nyilvános kulcsoddal titkosít egy üzenetet, és azt csak te tudod a titkos kulcsoddal elolvasni.
- Aláírás: te a titkos kulcsoddal aláírsz valamit, és bárki a nyilvános kulcsoddal ellenőrizheti, hogy tényleg te írtad alá, és nem módosították.

A nyilvános kulcsból a titkos kulcs gyakorlatilag nem számolható ki, ezért lehet a nyilvánosat szabadon terjeszteni.

Az RSA lassú, ezért

- Titkosításnál szimmetrukus kulcsot védenek vele
- Aláírásnál hash-t írnak alá

Alternatíva:

- DSA
- ECC, az elliptikus görbék diszkrét logaritmus problémáján alapul, legfontosabb alternatíva
    - ECDSA - böngészők ezt támogatják
    - Ed25519
- Poszt-kvantum algoritmusok
  - ML-KEM
  - ML-DSA
  - SLH-DSA

## PKI

- Honnan tudom, hogy egy publikus kulcs tényleg annak a személynek, szervernek vagy eszköznek a kulcsa, akinek mondja magát?
- A PKI-ban egy megbízható hitelesítésszolgáltató (CA) aláírásával kötik hozzá a publikus kulcsot az identitáshoz, ez a tanúsítvány

```shell
openssl req -x509 -subj "/CN=demo-cert-1" -keyout demo.key -out demo.crt \
  -sha256 -days 365 -noenc \
  -newkey ec -pkeyopt ec_paramgen_curve:prime256v1
```

Paraméterei:

- `-x509` önaláírt tanúsítványt készít
- `-out` tanúsítvány PEM formátumban
- `-noenc` - privát kulcsot nem titkosítja jelszóval

Szabványok alulról felfelé:

ASN.1 sémaleíró

```
FooProtocol DEFINITIONS ::= BEGIN

    FooQuestion ::= SEQUENCE {
        trackingNumber INTEGER,
        question       IA5String
    }

    FooAnswer ::= SEQUENCE {
        questionNumber INTEGER,
        answer         BOOLEAN
    }

END
```

X509 - tanúsítvány formátum

```
TBSCertificate  ::=  SEQUENCE  {
    version         [0]  EXPLICIT Version DEFAULT v1,
    serialNumber         CertificateSerialNumber,
    signature            AlgorithmIdentifier,
    issuer               Name,
    validity             Validity,
    subject              Name,
    subjectPublicKeyInfo SubjectPublicKeyInfo,
    issuerUniqueID  [1]  IMPLICIT UniqueIdentifier OPTIONAL,
                         -- If present, version MUST be v2 or v3
                          subjectUniqueID [2]  IMPLICIT UniqueIdentifier OPTIONAL,
                      -- If present, version MUST be v2 or v3
    extensions      [3]  EXPLICIT Extensions OPTIONAL
                      -- If present, version MUST be v3
}
```

DER

- ASN.1 encoding
- Distinguished Encoding Rules
- Bináris

PEM

- Egy objektumot tároló formátum
- Linux rendszereken elterjedt
- DER-ben kódolt érték BASE64-gyel kódolva
- `-----BEGIN CERTIFICATE-----` szövegek között

```shell
openssl x509 -in demo.crt -text -noout
```

PKCS#8

- privát kulcsot tároló formátum 
- Ugyanúgy ASN.1 séma és DER kódolás, BASE64-gyel
- (`-----BEGIN PRIVATE KEY-----`)

```shell
openssl pkey -in demo.key -text -noout
```

## Konténerek

- A PKCS#7 tanúsítványokat és láncokat szállít
    - `.p7b`, `.p7c` (aláírt/titkosított adatnál `.p7s`, `.p7m`)
    - PEM vagy DER
- A PKCS#12 pedig a privát kulcsot is tartalmazhatja, azaz kulcspárok szállítására is jó
    - Lehet jelszóvédelem
    - DER

```shell
openssl pkcs12 -export -inkey demo.key -in demo.crt -name demo-cert-1 -out demo.p12
```

```shell
openssl pkcs12 -in demo.p12 -info -noout
openssl pkcs12 -in demo.p12
```

- JKS (Java Key Store): Java 9 óta ne használjuk

## Tanúsíványlánc

https://www.training360.com/ https tanúsítvány

# HTTPS

## HTTPS

- Mögötte a TLS

## TLS

- Más protokollok titkosítására (HTTPS, SMTPS, IMAPS, stb.)
- X.509 tanúsítványok
- Elliptic Curve Diffie–Hellman (ECDHE) kulcscsere
- Modern Cypher: AES-GCM, ChaCha20-Poly1305
- Implementációja: OpenSSL

## mkcert

- mkcert egy eszköz helyi fejlesztői környezethez való, böngészők és rendszer által megbízhatónak tekintett TLS-tanúsítványok gyors előállítására

```shell
mkcert -install
mkcert example.com "*.example.com" example.test localhost 127.0.0.1 ::1
```

## Spring Boot HTTPS

```yaml
spring:
  ssl:
    bundle:
      pem:
        demo:
          reload-on-update: true
          keystore:
            certificate: "certs2/example.com+5.pem"
            private-key: "certs2/example.com+5-key.pem"
```

`https://localhost:8443/`

A kapcsolat biztonságos, tanúsítványlánc megnézhető

# Authentication

## Típusai

- Basic
- Digest
- Form alapú
- Kliens oldali tanúsítvány
- Fix token alapú (rendszer-rendszer)
- JWT token
- OAuth 2
    - Authorization Code Grant Type
    - Client Credentials

## Spring Security - Form alapú bejelentkezés

- Védelem URL alapján (csak `/**` védelem bejelentkezett felhasználóra (`.authenticated()`))
- Generate DB schema
- JPA
- Jelszótárolás csak plain textben

## OAuth 2

- Microservice környezet
- Public client/private client
- Keycloak

# Authorization

## Access Controll

- RBAC – Role-Based Access Control: felhasználónak szerepköre van, szerepkörhöz vannak jogosultságok rendelve. Egy felhasználóhoz több szerepkör is tartozhat.
- ABAC – Attribute-Based Access Control: döntés alapján születik
  - felhasználó attribútumai (department=HR, clearance=secret)
  - erőforrás attribútumai (doc.classification=confidential)
  - környezeti attribútumok (location=EU, time=business_hours)
  - Ezeket felhasználva komplex szabályok alapján: pl. "Engedélyezd, ha (user.department == resource.department) és time < 18:00."
- ACL - Minden objektumhoz (fájl, endpoint, adatbázis-rekord stb.) tartozik egy lista, amely megmondja, ki (mely felhasználó vagy csoport) mit tehet vele.

## Spring Security - Form alapú bejelentkezés

- URL based
- Method, annotation
- Programozott
- Felület
- [Spring Security ACL](https://www.jtechlog.hu/2010/07/11/spring-security-3-acl.html)
- ACL alternatíva [OAuth 2.0 szerepkörök és Domain Object Security](https://www.jtechlog.hu/2026/07/12/spring-security-oauth2-acl.html) alapján
- Domain Object Security [OAuth 2.0 szerepkörök és Domain Object Security](https://www.jtechlog.hu/2026/07/12/spring-security-oauth2-acl.html)