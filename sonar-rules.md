Java, Vulnerability - 60 db szabály

## RSPEC-7610 - Sensitive information should not be logged in production builds

Kliens oldalon, pl. Android nem szabad érzékeny adatokat naplózni, mint jelszó, stb.

## RSPEC-7606 - WebViews should not be vulnerable to cross-app scripting attacks

WebView - mobil alkalmazásban böngésző komponens
Cross-App Scripting - WebView-t támadja

Lsd. példa, JavaScript injektálás

## RSPEC-7518 - Privileged prompts should not be vulnerable to injection attacks

LLM-ek esetén szerepkörök: `system`, `user`, és `assistant`

Ezeknek jól meghatározott hierarchiájuk van. Ha felülírják pl. a `system` promptot,
akkor bármihez hozzáférhetnek, RAG adatokhoz, tool/MCP híváshoz.

## RSPEC-7044 - Server-side requests should not be vulnerable to traversing attacks

URL manipuláció

Lsd. példa, `id`-t ne konkatenáljuk az URL-be

## RSPEC-6549 - Accessing files should not lead to filesystem oracle attacks

Elérési utak konkatenációja

Lsd. példa, feloldja, majd megnézi, hogy kívül esik-e

## RSPEC-6547 - Environment variables should not be defined from untrusted input

Környezeti változókat ne állítsunk be megbízhatatlan forrás alapján

Lsd. példa, validáció, és csak értékként értelmezi a paramétert, kulcsot nem vesz át

## RSPEC-6437 - Credentials should not be hard-coded

Nem tárolunk jelszót a forrásban

## RSPEC-6432 - Counter Mode initialization vectors should not be reused

AES-GCM vagy AES-CCM titkosításnál az initialization vector (IV vagy nonce)
ne legyen újra felhasználva.

Lsd. példa, véletlenszerűen generál egyet

## RSPEC-6399 - XML operations should not be vulnerable to injection attacks

XML injection - XML konkatenáció, ha értékben relációs jeleket, tag-eket használunk

Lsd. példa, használunk lib-et

## RSPEC-6398 - JSON operations should not be vulnerable to injection attacks

JSON sem konkatenálható

## RSPEC-6390 - Thread suspensions should not be vulnerable to Denial of Service attacks

Valahogy kikényszerítik, hogy a `Thread` elfoglalt legyen.

Lsd. példa, a `Thread.sleep()` paramétere felhasználói inputból jön

## RSPEC-6384 - Components should not be vulnerable to intent redirection (Android)

## RSPEC-6377 - XML signatures should be validated securely

XML elektronikus aláírásnál magát az aláírást is ellenőrizni kell

Ez Java 17 előtt, alapból ki van kapcsolva.

Lsd. példa, hogy kell bekapcsolni

## RSPEC-6376 - XML parsers should not be vulnerable to Denial of Service attacks

Lsd. példa, be kell kapcsolni a biztonságos feldolgozást

## RSPEC-6374 - XML parsers should not load external schemas

Alapból ki van kapcsolva, ne kapcsoljuk vissza

## RSPEC-6373 - XML parsers should not allow inclusion of arbitrary files

Alapból ki van kapcsolva, maradjon is így

## RSPEC-6301

Android

## RSPEC-6287 - Applications should not create session cookies from untrusted input

Session cookie nem jöhet megbízhatatlan forrásból

Pl. ha van egy ilyen URL, mint a `https://example.com?cookie=injectedcookie`,
phishing technikával saját cookie-t rendelhet hozzá

## RSPEC-6173 - Reflection should not be vulnerable to injection attacks

Reflection vezérlése külső forrásból. Lsd. példa kód, le kell ellenőrizni,
hogy milyen class nevet engedünk be.

## RSPEC-6096 - Extracting archives should not lead to zip slip vulnerabilities

Lsd. kód, ZIP-ben lehetnek relatív elérések. Megoldás: `getCanonicalPath()`

## RSPEC-5883 - OS commands should not be vulnerable to argument injection attacks

Lsd. kód.
Ha parancssori paraméter konkatenálva van, lehet injection.
Tömbös átadási mód segít.

## RSPEC-5876 - A new session should be created during user authentication

A támadó előre meghatároz egy session ID-t, majd ráveszi az áldozatot, hogy ugyanazt a session ID-t használja bejelentkezéskor.

Igazából a védelmet nem szabad Spring Security-ban kikapcsolni.

Megoldás:

- Bejelentkezés után mindig új session ID-t kell adni.
- Session ID ne legyen beírható URL-be
- Csak HTTPOnly, Secure cookie használata

## RSPEC-5808 - Authorizations should be based on strong decisions

Egy `AccessDecisionVoter` implementációnak képesnek kell lennie arra, hogy nemet mondjon is (`ACCESS_DENIED`), különben nem felel meg a Spring Security elvárásainak.

## RSPEC-5679 - OpenSAML2 should be configured to prevent authentication bypass

Ha a parser a commenteket is figyelembe veszi, a támadó módosíthatja a mezőket.
Alapesetben nem veszi figyelembe, úgy kell hagyni.

## RSPEC-5659 - JWT should be signed and verified with strong cipher algorithms

Aláírni, megfelelő algoritmussal.

## RSPEC-5547 - Cipher algorithms should be robust

Erős algoritmusokat kell használni.

## RSPEC-5542 - Encryption algorithms should be used with secure mode and padding scheme

Secure mode: blokkok kezelése, ha külön kezeli a blokkokat, nem annyira biztonságos
Padding Scheme: ki kell tölteni a blokkot

## RSPEC-5527 - Server hostnames should be verified during SSL/TLS connections

Java SE esetén ne kapcsoljuk ki

## RSPEC-5496 - Server-side templates should not be vulnerable to injection attacks

Lsd. kód:

```html
<span th:text="${__${tainted}__}"></span>
```

Először kiértékeli a `tainted` értékét, és ez lesz a változónév.
Ha ez nem megbízható forrásból jön, nincs ellenőrizve, akkor bármi lehet.

## RSPEC-5445 - Insecure temporary file creation methods should not be used

`Files.createTempDirectory("")` atomi művelet, inkább ezt kell használni

## RSPEC-5344 - Passwords should not be stored in plaintext or with a fast hashing algorithm

## RSPEC-5334 - Dynamic code execution should not be vulnerable to injection attacks

## RSPEC-5301 - "ActiveMQConnectionFactory" should not be vulnerable to malicious code deserialization

Mint a RSPEC-5135, de itt queue-n jön be serialized objektum.

## RSPEC-5147 - NoSQL operations should not be vulnerable to injection attacks

## RSPEC-5146 - HTTP request redirections should not be open to forging attacks

Ilyen pl. a Keycloak Redirect URL, amit szintén validál

## RSPEC-5145 - Logging should not be vulnerable to injection attacks

Támadó sortörést helyez el az bevitt adatban, ezzel megzavarva a logelemzést

## RSPEC-5144 - Server-side requests should not be vulnerable to forging attacks

URL-t ne adhasson meg user, melyhez megy a kapcsolódás

Az RSPEC-5146 redirect, ez meg egy kapcsolatfelvétel.

## RSPEC-5135 - Deserialization should not be vulnerable to injection attacks

Mint az RSPEC-5301, de itt HTTP-n jön be serialized objektum.

# RSPEC-5131 - Endpoints should not be vulnerable to reflected cross-site scripting (XSS) attacks

Felhasználói bevitelt HTML encode-olva lehet csak megjeleníteni.

# RSPEC-4830 - Server certificates should be verified during SSL/TLS connections

Alapban be van kapcsolva, ne kapcsoljuk ki.

# RSPEC-4684 - Persistent entities should not be used as arguments of "@RequestMapping" methods

Entitást ne engedjünk ki a web rétegbe, mert olyan attribútumot módosíthat, amit nem akarunk.
Használjunk DTO-t.

# RSPEC-4601 - "HttpSecurity" URL patterns should be correctly ordered

Sorrendben vizsgálja meg, ahol először egyezés van, azt veszi alapul. Először mindig
a szigorúbb szabály legyen, utána a megengedőbb.

# RSPEC-4433 - LDAP connections should be authenticated

Lásd kód, úgy csatlakozzunk LDAP szerverhez, hogy legyen authentication

# RSPEC-4426 - Cryptographic keys should be robust

- RSA (Rivest-Shamir-Adleman) and DSA (Digital Signature Algorithm) esetén legalább 2048 bit, 3072 vagy 4096 előnyben részesített
- Elliptic Curve Cryptography (ECC) minimum 224 bit

# RSPEC-4423 - Weak SSL/TLS protocols should not be used

Ne használjunk "TLSv1.1"

# RSPEC-4347 - Secure random number generators should not output predictable values

Ne adjunk meg seedet

# RSPEC-3649 - Database queries should not be vulnerable to injection attacks

Klasszikus

# RSPEC-3329 - Cipher Block Chaining IVs should be unpredictable

Az IV ne legyen beégetett, pont az a lényege, hogy minden titkosításnál más legyen

# RSPEC-2755 - XML parsers should not be vulnerable to XXE attacks

Ki kell kapcsolni, hogy external entity-ket feldolgozzon

# RSPEC-2658 - Classes should not be loaded dynamically

Ne használjunk reflection-t

# RSPEC-2647 - Basic authentication should not be used

- Felhasználónév/jelszú a törzsben megy át BASE64-elve
- Használjunk pl. access tokent

# RSPEC-2631 - Regular expressions should not be vulnerable to Denial of Service attacks

- Felhasználó által megadott érték regex-ként kerül felhasználásra
- DoS: catastrophic backtracking
- Megoldás: `Pattern.quote(regex)`
- Megoldás: allowlist
- Megoldás: non-backtracking regex engines ([Google re2](https://github.com/google/re2), melynek elsődleges célja a biztonság)

# RSPEC-2254 - "HttpServletRequest.getRequestedSessionId()" should not be used

Ne a kliens által küldött session id-t használjuk, hanem a szerver által használtat

# RSPEC-2115 - A secure password should be used when connecting to a database

Ne égessük be a jelszót a forráskódba

# RSPEC-2091 - XPath expressions should not be vulnerable to injection attacks

- XPath-nál is van injection
- Itt sem konkatenálunk felhasználó által megadott értéket
- `setXPathVariableResolver()`

# RSPEC-2083 - I/O function calls should not be vulnerable to path injection attacks

- Path injection
- Megoldás: `normalize()`

# RSPEC-2078 - LDAP queries should not be vulnerable to injection attacks

- Szintén nem konkatenálunk
- `search()` metódus paraméterezhető

# RSPEC-2076 - OS commands should not be vulnerable to command injection attacks

- Nem hajtunk végre felhasználó által beküldött parancsot
- Megoldás: pl. whitelist

# RSPEC-2053 - Password hashing functions should use an unpredictable salt

Jelszónál a salt se legyen beégetett, hanem random

# RSPEC-1989 - Exceptions should not be thrown from servlet methods

Ne jussanak ki érzékeny információk a kivételből a felületre
