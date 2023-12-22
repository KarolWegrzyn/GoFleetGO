ConnectionManager:
odpowiada za otworzenie polaczenie do bazy danych i zwraca Connection

Classes:
każda klasa to odwzorowanie tabeli z bazy danych,
każda klasa zawiera podstawowe gettery i settery oraz ToString()

Repositories:
każda tabela ma swoje repository,
każde repository umożliwia podstawową komunikację z daną tabelą w bazie danych,

każda metoda insert przyjmuje obiekt reprezentujacy dana tabele i zapisuje go do bazy danych,
każda metoda findById przyjmuje (int)ID, odczytuje rekord z bazy i mapuje go na  zrozumialy dla java i zwraca obiekt,
