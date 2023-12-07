SONGIFY: APLIKACJA DO ZARZĄDZANIA ALBUMAMI, ARTYSTAMI I PIOSENKAMI

~~1. można dodać artystę (nazwa artysty)~~
~~2. można dodać gatunek muzyczny (nazwa gatunku)~~
~~3. można dodać album (tytuł, data wydania, ale musi być w nim przynajmniej jedna piosenka)~~
~~4. można dodać piosenkę (tytuł, czas trwania, data wydania, jezyk piosenki)~~
5. można usunąć artystę (usuwamy wtedy jego piosenki oraz albumy)
6. można usunąć gatunek muzyczny (ale nie może istnieć piosenka z takim gatunkiem)
7. można usunąć album (ale dopiero wtedy kiedy nie ma już żadnej piosenki przypisanej do albumu)
8. można usunąć piosenkę
9. można edytować piosenki artysty, oraz jego nazwę
10. można edytować nazwę gatunku muzycznego
11. można edytować album (dodawać piosenki, arytstów, zmieniac nazwe albumu)
12. można edytować piosenkę (czas trwania, artystę, nazwę piosenki)
13. można przypisać piosenki tylko do albumów
14. można przypisać artystów do albumów (album może mieć więcej artystów, artysta może mieć kilka albumów)
15. można przypisać tylko jeden gatunek muzyczny do piosenki
16. gdy nie ma przypisanego gatunku muzycznego do piosenki, to wyświetlamy "default"
17. można wyświetlać wszystkie piosenki
18. można wyświetlać wszystkie gatunki
~~19. można wyświetlać wszystkich artystów~~
20. można wyświetlać wszystkie albumy
21. można wyświetlać wszystkie albumy z artystami oraz piosenkami w albumie
22. można wyświetlać konkretne gatunki muzyczne wraz z piosenkami
23. można wyświetlać konkretnych artystów wraz z ich albumami
24. chcemy mieć trwałe dane


HAPPY PATH (user tworzy album "Eminema" z piosenkami "Til i collapse", "Lose Yourself", o gatunku Rap)

given there is no songs, artists, albums and genres created before

1. when I go to /song then I can see no songs
2. when I post to /song with Song "Till i collapse" then Song "Til i collapse" is returned with id 1
3. when I post to /song with Song "Lose Yourself" then Song "Lose Yourself" is returned with id 2
4. when I go to /genre then I can see no genres
5. when I post to /genre with Genre "Rap" then Genre "Rap" is returned with id 1
6. when I go to /song/1 then I can see default genre
7. when I put to /song/1/genre/1 then Genre with id 1 ("Rap") is added to Song with id 1 ("Til i collapse")
8. when I go to /song/1 then I can see "Rap" genre
9. when I put to /song/2/genre/1 then Genre with id 1 ("Rap") is added to Song with id 2 ("Lose Yourself")
10. when I go to /album then I can see no albums
11. when I post to /album with Album "EminemAlbum1" and Song with id 1 then Album "EminemAlbum1" is returned with id 1
12. when I go to /album/1 then I can see song with id 1 added to it
13. when I put to /album/1/song/1 then Song with id 1 ("Til i collapse") is added to Album with id 1 ("EminemAlbum1")
14. when I put to /album/1/song/2 then Song with id 2 ("Lose Yourself") is added to Album with id 1 ("EminemAlbum1")
15. when I go to /album/1/song then I can see 2 songs (id 1, id 2)
16. when I post to /artist with Artist "Eminem" then Artist "Eminem" is returned with id 1
17. when I put to /album/1/artist/2 then Artist with id 1 ("Eminem") is added to Album with id 1 ("EminemAlbum1")
