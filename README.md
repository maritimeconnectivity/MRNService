# MRNService
A service for MRN validation

## What is MRN?
Maritime Resource Name (MRN) is a naming scheme under Uniform Resource Name (URN) that can uniquely identify any maritime resource on a global scale, hosted by IALA.
This implementation is based on the version 1 of MRN proposed by Kasper Nielsen. (https://www.iana.org/assignments/urn-formal/mrn)

## How to use
Assuming you are deploying this on your local machine, you can call Get request as follows:

  localhost:8080/validate?mrn=urn:mrn:mcp:user:geek:jinki

Then it will check whether it follows the MRN syntax or not and return the validation result as json format.

  {
      "result": true,
      "mrn": "urn:mrn:mcp:user:geek:jinki"
  }
  
But with wrong scheme like having 'm' for <OID>,

  localhost:8080/validate?mrn=urn:mrn:m:user:genius:oliver

Then you will get,

  {
      "result": false,
      "mrn": "urn:mrn:m:user:genius:oliver"
  }
  
### When you have your specific MRN syntax
Assuming you are deploying this on your local machine, you can call Post request with a json data body, e.g.,

  {
	  "mrn":"urn:mrn:mcp:user:granpa:thomas",
  	"regex":"^[Uu][Rr][Nn]\\:[Mm][Rr][Nn]\\:[Mm][Cc][Pp]\\:([Dd][Ee][Vv][Ii][Cc][Ee]|[Oo][Rr][Gg]|[Uu][Ss][Ee][Rr]|[Vv][Ee][Ss][Ss][Ee][Ll]|[Ss][Ee][Rr][Vv][Ii][Cc][Ee])\\:([A-Za-z0-9]([A-Za-z0-9]|\\-){0,20}[A-Za-z0-9])\\:((([-A-Z._a-z0-9]|~)|%[0-9A-Fa-f][0-9A-Fa-f]|(\\!|\\$|&|'|\\(|\\)|\\*|\\+|,|;|\\=)|\\:|@)((([-A-Z._a-z0-9]|~)|%[0-9A-Fa-f][0-9A-Fa-f]|(\\!|\\$|&|'|\\(|\\)|\\*|\\+|,|;|\\=)|\\:|@)|\\/)*)$"
  }

which checks the MRN follows MCP MRN syntax. This case you will get,

  {
      "result": true,
      "mrn": "urn:mrn:mcp:user:granpa:thomas"
  }

## Prerequisite
The MRN validation of this implementation is based on the regular expression (REGEX), which can be obtained from a MRN syntax through several steps.
In the example with the official MRN syntax,

      <MRN>   ::= "urn" ":" "mrn" ":" <OID> ":" <OSS>
                  [ rq-components ]
                  [ "#" f-component ]
      <OID>   ::= (alphanum) 0*20(alphanum / "-") (alphanum) ; 
                  Organization ID
      <OSS>   ::= <OSNID> ":" <OSNS> ; Organization-specific string
      <OSNID> ::= (alphanum) 0*32(alphanum / "-") (alphanum) 
                  ; Organization-specific namespace ID
      <OSNS>  ::= pchar *(pchar / "/") ; Organization-specific 
                  namespace string
                  
This could be converted to the pure Augmented Backus–Naur form (ABNF) as below:

  mrn = "urn" ":" "mrn" ":" oid ":" oss [rq-components] [ "#" f-component ]
  oid = (alphanum) 0*20((alphanum) / "-") (alphanum) ; Organization ID
  oss = osnid ":" osns ; Organization-specific string
  osnid = (alphanum) 0*20((alphanum) / "-") (alphanum) ; Organization-specific namespace ID
  osns = pchar *(pchar / "/") ; Organization-specific namespace string
  rq-components = [ "?+" r-component ][ "?=" q-component ] ; rfc8141
  r-component = pchar *( pchar / "/" / "?" ) ; rfc8141
  q-component = pchar *( pchar / "/" / "?" ) ; rfc8141
  f-component   = fragment ; rfc8141
  fragment = *( pchar / "/" / "?" ) ; ; rfc3986
  alphanum = ALPHA / DIGIT ; rfc3986
  pchar = unreserved / pct-encoded / sub-delims / ":" / "@" ; rfc3986
  unreserved = ALPHA / DIGIT / "-" / "." / "_" / "~" ; rfc3986
  pct-encoded = "%" HEXDIG HEXDIG ; rfc3986
  sub-delims = "!" / "$" / "&" / "'" / "(" / ")" / "*" / "+" / "," / ";" / "=" ; rfc3986

You can practice the generation of valid ABNF and corresponding REGEX by online tools such as https://abnf.msweet.org/.

The corresponding raw REGEX of MRN syntax is:

  mrn: ^[Uu][Rr][Nn]\:[Mm][Rr][Nn]\:([A-Za-z0-9]([A-Za-z0-9]|\-){0,20}[A-Za-z0-9])\:([A-Za-z0-9][-A-Za-z0-9]{0,20}[A-Za-z0-9])\:((([-A-Z._a-z0-9]|~)|%[0-9A-Fa-f][0-9A-Fa-f]|(\!|\$|&|'|\(|\)|\*|\+|,|;|\=)|\:|@)((([-A-Z._a-z0-9]|~)|%[0-9A-Fa-f][0-9A-Fa-f]|(\!|\$|&|'|\(|\)|\*|\+|,|;|\=)|\:|@)|/)*)((\?\+((([-A-Z._a-z0-9]|~)|%[0-9A-Fa-f][0-9A-Fa-f]|(\!|\$|&|'|\(|\)|\*|\+|,|;|\=)|\:|@)((([-A-Z._a-z0-9]|~)|%[0-9A-Fa-f][0-9A-Fa-f]|(\!|\$|&|'|\(|\)|\*|\+|,|;|\=)|\:|@)|/|\?)*))?(\?\=((([-A-Z._a-z0-9]|~)|%[0-9A-Fa-f][0-9A-Fa-f]|(\!|\$|&|'|\(|\)|\*|\+|,|;|\=)|\:|@)((([-A-Z._a-z0-9]|~)|%[0-9A-Fa-f][0-9A-Fa-f]|(\!|\$|&|'|\(|\)|\*|\+|,|;|\=)|\:|@)|/|\?)*))?)?(#(((([-A-Z._a-z0-9]|~)|%[0-9A-Fa-f][0-9A-Fa-f]|(\!|\$|&|'|\(|\)|\*|\+|,|;|\=)|\:|@)|/|\?)*))?$

You can also practice the REGEX you made and MRN following it by online tools such as https://regex101.com/.


## Local deployment
In the root folder of the repository,

  mvn clean install

then run with

  java -jar target/mrnValidation-0.7.0-SNAPSHOT.war

## Future implementation
We will implement 'generate' api with supports of specific MRN syntax in near future.
