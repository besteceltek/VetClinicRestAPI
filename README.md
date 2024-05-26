## Projenin Eksikleri

Şu an için Randevu güncelleme işlemi yazılmadı ve alınan bazı exceptionlar için
custom exception yazılmadı. Onun dışında bütün fonksiyonları çalışıyor. Bütün isterleri
eksiksiz yerine getirip getirmediği kontrol edilecek. Readme dosyası eklenecek.
UML diyagramı güncellenip eklenecek.


Animal

- id:Long

- name:String

- species:String

- breed:String

- gender:String

- colour:String

- dateOfBirth:LocalDate

Customer

- id:Long

- name:String

- phone:String

- mail: String

- address:String

- city:String

Vaccine

- id: Long

- name: String 

- code: String

- protectionStartDate: LocalDate

- protectionFinishDate: LocalDate

Doctor

- id:Long

- name:String

- phone:String

- mail: String

- address:String

- city:String

AvailableDate

- id:Long

- availableDate:LocalDate

Appointment

- id:Long

- appointmentDate:LocalDateTime