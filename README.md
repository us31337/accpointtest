Тестовое задание
Существует база данных PostgreSQL, которая содержит следующую таблицу объектов:
CREATE TABLE objects
(
id bigint NOT NULL DEFAULT nextval('objects_id_seq'::regclass),
uid text COLLATE pg_catalog."default" NOT NULL DEFAULT uuid_generate_v1(),
object_type integer NOT NULL,
data jsonb,
parent_object_id bigint NOT NULL DEFAULT 0,
CONSTRAINT objects_pkey PRIMARY KEY (id)
)
Объекты организованы в виде дерева через связь parent_object_id → id.
Каждый объект имеет свой тип (поле object_type), заданный целым числом.
В поле data хранится объект JSON, который содержит строковое свойство name (но в некоторых
случаях свойство может отсутствовать).
{
...
"name": "some name",
...
}
На языке программирования Java необходимо разработать приложение, которое
• подключается к описанной БД
• считывает объекты
• формирует HTML файл, который отображает:
◦ представляемое объектами дерево
◦ статистику по количеству узлов каждого типа
В качестве текста узла дерева использовать свойство name при его наличии, иначе поле uid.
