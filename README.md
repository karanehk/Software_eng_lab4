# Software_eng_lab2

# بازآرایی کد (Code Refactoring)

---

## روال انجام آزمایش
در این آزمایش قرار است یک پروژه را با هدف افزایش خوانایی و کارایی آن از طریق تغییر در ساختار برنامه بدون اینکه رفتار خارجی آن تغییر کند، بازآزایی کنیم. در ابتدا برنامه را اجرا کرده و خروجی آن را ذخیره می‌کنیم و بعد از هر مرحله بازآرایی، دوباره از برنامه خروجی می‌گیریم تا مطمئن شویم که با اعمال این تغییرات، رفتار خارجی برنامه تغییری نکرده باشد. حال بازآرایی‌های خواسته شده را یک به یک اعمال می‌کنیم:

### اعمال الگوی Facade:
این الگو، کلاسی است که یک رابط ساده برای یک زیرسیستم پیچیده که شامل قطعات بسیاریست فراهم می‌کند. یک facade ممکن است عملکرد محدودی را در مقایسه با کار مستقیم با زیرسیستم ارائه دهد. با این حال قطعا شامل ویژگی‌هایی خواهد بود که کلاینت به آن‌ها اهمیت داده و استفاده می‌کند.
#### ۱) مورد اول، `CodeGenerator`:
در `Parser` می‌توان دید که زیرسیستم `CodeGenerator` به طور مستقیم استفاده می‌شود تا از توابع آن استفاده کند. برای کاهش پیچیدگی، یک کلاس `CodeGeneratorFacade` می‌سازیم و تنها توابعی که کلاینت قرار است از آن‌ها استفاده کند را در آن قرار می‌دهیم و در آن توابع، توابع اصلی‌ای که در خود کلاس `CodeGenerator` هستند را صدا می‌زنیم. این تغییر را می‌توانید در زیر مشاهده کنید:

<img width="979" alt="Screenshot 1402-05-21 at 2 57 44 PM" src="https://github.com/karanehk/software_eng_lab4/assets/59361911/90343a83-ea71-433b-a9d9-fd1575e31c25">
<img width="1201" alt="Screenshot 1402-05-21 at 3 04 06 PM" src="https://github.com/karanehk/software_eng_lab4/assets/59361911/df08e1d1-d3bf-49b1-849f-b0145dbc2d81">


#### ۲) مورد دوم، `Memory`:
در این مورد هم مانند مورد قبلی، `SymbolTable` به طور مستقیم از `Memory` استفاده می‌کند. پس یک `MemoryFacade` می‌سازیم و تغییرات مورد نیاز را اعمال می‌کنیم:

<img width="811" alt="Screenshot 1402-05-21 at 3 06 39 PM" src="https://github.com/karanehk/software_eng_lab4/assets/59361911/ce50c6de-08c1-4850-95b6-595349d421a8">
<img width="1317" alt="Screenshot 1402-05-21 at 3 06 57 PM" src="https://github.com/karanehk/software_eng_lab4/assets/59361911/0dd91dd9-56b2-4ac9-90ad-323cfa43e3ca">
<img width="1319" alt="Screenshot 1402-05-21 at 3 07 45 PM" src="https://github.com/karanehk/software_eng_lab4/assets/59361911/69c40094-4863-49b6-8fc0-7de15805defe">

### ۳) اعمال الگوی استفاده از Polymorphism به جای شرط:
طیق این الگو، شرط‌های `switch-case‍` را بازآرایی کرده و جای آن، کلاس‌های فرعی جدیدی می‌سازیم که هر یک متناظر با یکی از caseها باشند. برای مثال در کد اولیه، یک `enum` با عنوان `TypeAddress` داریم که در کلاس `Address` و حین اجرای `toString`، روی انواع آن `switch-case‍` زده شده، پس هر یک را به یک کلاس جدید تبدیل می‌کنیم:

<img width="1317" alt="Screenshot 1402-05-21 at 3 17 10 PM" src="https://github.com/karanehk/software_eng_lab4/assets/59361911/12ec8239-6856-4e8d-a6eb-abd87f33bd2f">
<img width="1317" alt="Screenshot 1402-05-21 at 3 17 25 PM" src="https://github.com/karanehk/software_eng_lab4/assets/59361911/e8e8910c-adcd-48ff-90c0-1d3d3f679591">
<img width="1316" alt="Screenshot 1402-05-21 at 3 17 34 PM" src="https://github.com/karanehk/software_eng_lab4/assets/59361911/47fd008c-f686-41c2-888b-134933f809cb">
<img width="1318" alt="Screenshot 1402-05-21 at 3 17 44 PM" src="https://github.com/karanehk/software_eng_lab4/assets/59361911/a1646525-81d1-498f-b9ae-e574b79ac350">
<img width="1410" alt="Screenshot 1402-05-21 at 3 18 39 PM" src="https://github.com/karanehk/software_eng_lab4/assets/59361911/25090aee-3eb5-4553-98df-cb7f39a0de15">
<img width="1410" alt="Screenshot 1402-05-21 at 3 18 51 PM" src="https://github.com/karanehk/software_eng_lab4/assets/59361911/5bef959b-b8e7-418d-aca5-1e3e1484872b">
<img width="1412" alt="Screenshot 1402-05-21 at 3 19 02 PM" src="https://github.com/karanehk/software_eng_lab4/assets/59361911/e0c5c12e-7f60-41db-b91c-21364f152fe3">

### ۴) اعمال الگوی Separate Query From Modifier:
این الگو، CQRS را پیاده سازی می‌کند. این اصل به ما می‌گوید که کدهای مسئول دریافت داده‌ها را از کدهایی که چیزی را در داخل یک شی تغییر می‌دهند جدا کنیم. برای مثال این مورد در `SymbolTable` در تابع ‍`getNextParameter` وجود داشت که به شکل زیر آن را بازآرایی کردیم:

<img width="1406" alt="Screenshot 1402-05-21 at 3 24 58 PM" src="https://github.com/karanehk/software_eng_lab4/assets/59361911/79e5a295-ed5f-4fe9-a293-3fcefbfc1790">
<img width="1409" alt="Screenshot 1402-05-21 at 3 25 09 PM" src="https://github.com/karanehk/software_eng_lab4/assets/59361911/4aaadd0c-f381-4b4c-ab32-66b6515568c5">

### ۵) اعمال الگوی Self Encapsulate Field:
این الگو مربوط به مواردیست که در توابع یک کلاس، به طور مستقیم از متغیرهای خصوصی آن استفاده می‌شود. گاهی اوقات این دسترسی مستقیم در داخل یک کلاس به اندازه کافی انعطاف پذیر نیست. برای مثال در `lexicalAnalyzer` دسترسی مستقیم به متغیر خصوصی `matcher` وجود داشت که آن را به شکل زیر تغییر دادیم:

<img width="1408" alt="Screenshot 1402-05-21 at 3 33 00 PM" src="https://github.com/karanehk/software_eng_lab4/assets/59361911/a0663a09-115d-481c-93d8-a0416bacc247">

### ۶) اعمال الگوی Separate Query From Modifier، دومین مورد:
توضیحات این بازآرایی در بالا آمده است. در `Memory` و تابع `getDataAddress` نیز مشکل دریافت داده و تغییر همزمان آن دیده می‌شود. بنابراین آن را به شکل زیر تغییر می‌دهیم. باید دقت شود که از آن‌جا که برای `Memory` یک `'Facade` ساخته بودیم، تغییرات را در آن نیز باید اعمال کنیم:

<img width="1269" alt="Screenshot 1402-05-21 at 3 38 50 PM" src="https://github.com/karanehk/software_eng_lab4/assets/59361911/b3c68c83-dbf5-4d76-8b21-76b3cca499a0">
<img width="1270" alt="Screenshot 1402-05-21 at 3 39 00 PM" src="https://github.com/karanehk/software_eng_lab4/assets/59361911/f7541e42-4d2e-4fdb-a94a-d9b53ca58f9a">
<img width="1412" alt="Screenshot 1402-05-21 at 3 39 23 PM" src="https://github.com/karanehk/software_eng_lab4/assets/59361911/d0473422-a742-4163-b145-4a5a1257aa81">

### ۷) اعمال الگوی inline temp:
وقتی یک متغیر موقت داریم که نتیجه‌ی یک عبارت ساده به آن اختصاص داده می‌شود و هیچ کارایی بیش‌تری ندارد، می‌توانیم ارجاعات متغیر را با خود عبارت جایگزین کنیم. این مورد در `Token` و تابع `hashCode` دیده می‌شد که آن را به شکل زیر تغییر دادیم:

<img width="1409" alt="Screenshot 1402-05-21 at 3 45 25 PM" src="https://github.com/karanehk/software_eng_lab4/assets/59361911/a011721e-4f69-47e5-a9b8-dcb959c78b2d">





