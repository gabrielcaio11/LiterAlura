-- Limpando dados das tabelas relacionadas para evitar conflitos
TRUNCATE TABLE person_book CASCADE;
TRUNCATE TABLE tb_persons CASCADE;
TRUNCATE TABLE tb_books CASCADE;

-- Inserindo registros na tabela tb_persons
INSERT INTO tb_persons (id, birth_year, death_year, name)
VALUES 
    (1, 1952, NULL, 'Joshua Bloch'),   -- Autor de 'Effective Java'
    (2, 1961, NULL, 'Robert C. Martin'), -- Autor de 'Clean Code'
    (3, 1906, 1994, 'Erich Gamma');   -- Co-autor de 'Design Patterns'

-- Inserindo registros na tabela tb_books
INSERT INTO tb_books (id, title, download_count, languages)
VALUES 
    (1, 'Effective Java', 5000, ARRAY['English', 'Portuguese']),
    (2, 'Clean Code', 7500, ARRAY['English']),
    (3, 'Design Patterns: Elements of Reusable Object-Oriented Software', 3000, ARRAY['French', 'German']);

-- Relacionando autores (persons) e livros na tabela intermediária person_book
INSERT INTO person_book (person_id, book_id)
VALUES 
    (1, 1), -- Joshua Bloch escreveu 'Effective Java'
    (2, 2), -- Robert C. Martin escreveu 'Clean Code'
    (3, 3), -- Erich Gamma escreveu 'Design Patterns'
    (1, 3); -- Joshua Bloch também contribuiu para 'Design Patterns'
