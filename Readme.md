The CWEs can be checked here: https://cwe.mitre.org/top25/archive/2022/2022_cwe_top25.html

1.	CWE-787	Out-of-bounds Write -> Custom  
2.	CWE-79	Improper Neutralization of Input During Web Page Generation ('Cross-site Scripting') -> Custom
3.	CWE-89	Improper Neutralization of Special Elements used in an SQL Command ('SQL Injection') -> Custom aber vorhanden
4.	CWE-20	Improper Input Validation -> wird nicht getestet da mit 77, 79, 89, 94 schon teilweise abgedeckt
5.	CWE-125	Out-of-bounds Read -> Custom
6.	CWE-78	Improper Neutralization of Special Elements used in an OS Command ('OS Command Injection') -> Custom aber vorhanden
7.	CWE-416	Use After Free -> wird nicht getestet da laut CWE nicht java relevant
8.	CWE-22	Improper Limitation of a Pathname to a Restricted Directory ('Path Traversal') -> Custom
9.	CWE-352	Cross-Site Request Forgery (CSRF) -> custom
10.	CWE-434	Unrestricted Upload of File with Dangerous Type -> custom
11.	CWE-476	NULL Pointer Dereference -> custom
12.	CWE-502	Deserialization of Untrusted Data -> custom
13.	CWE-190	Integer Overflow or Wraparound -> custom
14.	CWE-287	Improper Authentication -> wird nicht getestet da automated static analysis tools have difficulty detecting custom authentication schemes. In addition, the software's design may include some functionality that is accessible to any user and does not require an established identity; an automated technique that detects the absence of authentication may report false positives.
15.	CWE-798	Use of Hard-coded Credentials -> custom
16.	CWE-862	Missing Authorization -> wird nicht getestet da Generally, automated static analysis tools have difficulty detecting custom authorization schemes. In addition, the software's design may include some functionality that is accessible to any user and does not require an authorization check; an automated technique that detects the absence of authorization may report false positives.
17.	CWE-77	Improper Neutralization of Special Elements used in a Command ('Command Injection') -> custom, aber ähnlich zu cw78
18.	CWE-306	Missing Authentication for Critical Function -> wird nicht getestet da Generally, automated static analysis tools have difficulty detecting custom authentication schemes. In addition, the software's design may include some functionality that is accessible to any user and does not require an established identity; an automated technique that detects the absence of authentication may report false positives.
19.	CWE-119	Improper Restriction of Operations within the Bounds of a Memory Buffer -> custom
20.	CWE-276	Incorrect Default Permissions -> custom
21.	CWE-918	Server-Side Request Forgery (SSRF) -> custom
22.	CWE-362	Concurrent Execution using Shared Resource with Improper Synchronization ('Race Condition') -> custom
23.	CWE-400	Uncontrolled Resource Consumption -> custom
24.	CWE-611	Improper Restriction of XML External Entity Reference -> custom
25.	CWE-94	Improper Control of Generation of Code ('Code Injection') -> wird nicht getestet, da mit 77, 78, 79, 89 und 611 schon Code Injection Beispiele gegeben sind