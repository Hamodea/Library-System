--
-- PostgreSQL database dump
--

-- Dumped from database version 17.5
-- Dumped by pg_dump version 17.5

-- Started on 2025-06-07 22:16:57

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 218 (class 1259 OID 16856)
-- Name: authors; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.authors (
    author_id bigint NOT NULL,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    birth_year integer,
    nationality character varying(255)
);


ALTER TABLE public.authors OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16855)
-- Name: authors_author_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.authors_author_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.authors_author_id_seq OWNER TO postgres;

--
-- TOC entry 4935 (class 0 OID 0)
-- Dependencies: 217
-- Name: authors_author_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.authors_author_id_seq OWNED BY public.authors.author_id;


--
-- TOC entry 220 (class 1259 OID 16863)
-- Name: books; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.books (
    book_id bigint NOT NULL,
    title character varying(255) NOT NULL,
    publication_year integer,
    available_copies integer DEFAULT 1,
    total_copies integer DEFAULT 1,
    author_id bigint
);


ALTER TABLE public.books OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16862)
-- Name: books_book_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.books_book_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.books_book_id_seq OWNER TO postgres;

--
-- TOC entry 4936 (class 0 OID 0)
-- Dependencies: 219
-- Name: books_book_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.books_book_id_seq OWNED BY public.books.book_id;


--
-- TOC entry 223 (class 1259 OID 16889)
-- Name: loans; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.loans (
    user_id bigint NOT NULL,
    book_id bigint NOT NULL,
    borrowed_date timestamp without time zone NOT NULL,
    due_date timestamp without time zone NOT NULL,
    returned_date timestamp without time zone,
    loan_id bigint NOT NULL
);


ALTER TABLE public.loans OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 16937)
-- Name: loans_loan_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.loans_loan_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.loans_loan_id_seq OWNER TO postgres;

--
-- TOC entry 4937 (class 0 OID 0)
-- Dependencies: 224
-- Name: loans_loan_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.loans_loan_id_seq OWNED BY public.loans.loan_id;


--
-- TOC entry 222 (class 1259 OID 16877)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    user_id bigint NOT NULL,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    registration_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16876)
-- Name: users_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_user_id_seq OWNER TO postgres;

--
-- TOC entry 4938 (class 0 OID 0)
-- Dependencies: 221
-- Name: users_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_user_id_seq OWNED BY public.users.user_id;


--
-- TOC entry 4757 (class 2604 OID 16859)
-- Name: authors author_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.authors ALTER COLUMN author_id SET DEFAULT nextval('public.authors_author_id_seq'::regclass);


--
-- TOC entry 4758 (class 2604 OID 16866)
-- Name: books book_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.books ALTER COLUMN book_id SET DEFAULT nextval('public.books_book_id_seq'::regclass);


--
-- TOC entry 4763 (class 2604 OID 16938)
-- Name: loans loan_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.loans ALTER COLUMN loan_id SET DEFAULT nextval('public.loans_loan_id_seq'::regclass);


--
-- TOC entry 4761 (class 2604 OID 16880)
-- Name: users user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN user_id SET DEFAULT nextval('public.users_user_id_seq'::regclass);


--
-- TOC entry 4923 (class 0 OID 16856)
-- Dependencies: 218
-- Data for Name: authors; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.authors (author_id, first_name, last_name, birth_year, nationality) FROM stdin;
1	Astrid	Lindgren	1907	Swedish
2	Stieg	Larsson	1954	Swedish
3	Camilla	Läckberg	1974	Swedish
4	Henning	Mankell	1948	Swedish
5	Selma	Lagerlöf	1858	Swedish
6	August	Strindberg	1849	Swedish
7	Pär	Lagerkvist	1891	Swedish
8	Vilhelm	Moberg	1898	Swedish
9	Hjalmar	Söderberg	1869	Swedish
10	Tove	Jansson	1914	Finnish-Swedish
11	Agatha	Christie	1890	British
12	Arthur Conan	Doyle	1859	British
13	J.K.	Rowling	1965	British
14	George	Orwell	1903	British
15	Jane	Austen	1775	British
16	Charles	Dickens	1812	British
17	Virginia	Woolf	1882	British
18	Oscar	Wilde	1854	Irish
19	James	Joyce	1882	Irish
20	Bram	Stoker	1847	Irish
21	Leo	Tolstoy	1828	Russian
22	Fyodor	Dostoevsky	1821	Russian
23	Anton	Chekhov	1860	Russian
24	Vladimir	Nabokov	1899	Russian-American
25	Ernest	Hemingway	1899	American
26	Mark	Twain	1835	American
27	F. Scott	Fitzgerald	1896	American
28	Harper	Lee	1926	American
29	John	Steinbeck	1902	American
30	Kurt	Vonnegut	1922	American
31	Gabriel García	Márquez	1927	Colombian
32	Jorge Luis	Borges	1899	Argentine
33	Paulo	Coelho	1947	Brazilian
34	Isabel	Allende	1942	Chilean
35	Milan	Kundera	1929	Czech
36	Franz	Kafka	1883	Czech
37	Hermann	Hesse	1877	German
38	Thomas	Mann	1875	German
39	Umberto	Eco	1932	Italian
40	Haruki	Murakami	1949	Japanese
\.


--
-- TOC entry 4925 (class 0 OID 16863)
-- Dependencies: 220
-- Data for Name: books; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.books (book_id, title, publication_year, available_copies, total_copies, author_id) FROM stdin;
1	Pippi Longstocking	1945	2	3	1
3	The Ice Princess	2003	2	2	3
4	Faceless Killers	1991	1	2	4
7	Barabbas	1950	1	1	7
9	Doctor Glas	1905	1	1	9
10	The Moomins and the Great Flood	1945	2	2	10
11	Murder on the Orient Express	1934	1	2	11
12	The Adventure of Sherlock Holmes	1892	2	3	12
13	Harry Potter and the Philosopher''s Stone	1997	0	2	13
14	1984	1949	1	2	14
15	Pride and Prejudice	1813	2	2	15
16	Great Expectations	1861	1	2	16
17	To the Lighthouse	1927	1	1	17
18	The Picture of Dorian Gray	1890	1	1	18
19	Ulysses	1922	1	1	19
20	Dracula	1897	1	2	20
21	War and Peace	1869	1	1	21
22	Crime and Punishment	1866	1	2	22
23	The Cherry Orchard	1904	1	1	23
24	Lolita	1955	1	1	24
25	The Old Man and the Sea	1952	1	2	25
26	Adventures of Huckleberry Finn	1884	2	2	26
27	The Great Gatsby	1925	0	2	27
28	To Kill a Mockingbird	1960	1	2	28
29	The Grapes of Wrath	1939	1	1	29
30	Slaughterhouse-Five	1969	1	1	30
31	One Hundred Years of Solitude	1967	1	1	31
32	Labyrinths	1962	1	1	32
33	The Alchemist	1988	2	2	33
34	The House of the Spirits	1982	1	1	34
35	The Unbearable Lightness of Being	1984	1	1	35
36	The Metamorphosis	1915	1	2	36
37	Steppenwolf	1927	1	1	37
38	The Magic Mountain	1924	1	1	38
39	The Name of the Rose	1980	1	1	39
40	Norwegian Wood	1987	1	2	40
41	The Girl Who Played with Fire	2006	1	1	2
42	The Preacher	2004	1	1	3
43	The White Lioness	1993	1	1	4
44	Gösta Berling''s Saga	1891	1	1	5
45	Miss Julie	1888	1	1	6
46	The Dwarf	1944	1	1	7
47	The Last Letter	1959	1	1	8
48	Gertrud	1906	1	1	9
49	Finn Family Moomintroll	1948	1	1	10
50	And Then There Were None	1939	1	1	11
8	The Emigrants	1949	0	2	8
6	The Red Room	1879	0	2	6
5	The Wonderful Adventures of Nils	1906	3	3	5
2	The Girl with the Dragon Tattoo	2005	0	2	2
\.
--
-- TOC entry 4928 (class 0 OID 16889)
-- Dependencies: 223
-- Data for Name: loans; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.loans (user_id, book_id, borrowed_date, due_date, returned_date, loan_id) FROM stdin;
1	1	2024-01-15 10:00:00	2024-01-29 10:00:00	2024-01-25 14:30:00	1
2	5	2024-01-20 11:00:00	2024-02-03 11:00:00	2024-02-01 09:15:00	2
3	8	2024-01-25 09:30:00	2024-02-08 09:30:00	2024-02-05 16:45:00	3
4	12	2024-02-01 14:00:00	2024-02-15 14:00:00	2024-02-12 11:20:00	4
5	15	2024-02-05 13:30:00	2024-02-19 13:30:00	2024-02-18 10:00:00	5
6	20	2024-02-10 08:45:00	2024-02-24 08:45:00	2024-02-22 15:30:00	6
7	25	2024-02-15 16:15:00	2024-03-01 16:15:00	2024-02-28 12:45:00	7
8	30	2024-02-20 10:30:00	2024-03-06 10:30:00	2024-03-04 14:20:00	8
9	35	2024-02-25 12:00:00	2024-03-11 12:00:00	2024-03-09 09:30:00	9
10	40	2024-03-01 15:45:00	2024-03-15 15:45:00	2024-03-13 11:15:00	10
1	2	2024-03-10 09:00:00	2024-03-24 09:00:00	\N	11
2	7	2024-03-12 14:30:00	2024-03-26 14:30:00	\N	12
3	11	2024-03-15 11:15:00	2024-03-29 11:15:00	\N	13
4	16	2024-03-18 13:45:00	2024-04-01 13:45:00	\N	14
5	21	2024-03-20 10:30:00	2024-04-03 10:30:00	\N	15
6	26	2024-03-22 15:00:00	2024-04-05 15:00:00	\N	16
7	31	2024-03-24 12:30:00	2024-04-07 12:30:00	\N	17
8	36	2024-03-26 09:45:00	2024-04-09 09:45:00	\N	18
9	41	2024-03-28 14:00:00	2024-04-11 14:00:00	\N	19
10	45	2024-03-30 11:30:00	2024-04-13 11:30:00	\N	20
1	3	2024-02-05 08:30:00	2024-02-19 08:30:00	2024-02-17 13:00:00	21
2	6	2024-02-08 10:15:00	2024-02-22 10:15:00	2024-02-20 15:30:00	22
3	9	2024-02-12 13:45:00	2024-02-26 13:45:00	2024-02-24 09:45:00	23
4	14	2024-02-15 09:00:00	2024-03-01 09:00:00	2024-02-28 14:15:00	24
5	17	2024-02-18 14:30:00	2024-03-04 14:30:00	2024-03-02 11:00:00	25
6	22	2024-02-20 11:45:00	2024-03-06 11:45:00	2024-03-04 16:30:00	26
7	24	2024-02-22 16:00:00	2024-03-08 16:00:00	2024-03-06 12:15:00	27
8	28	2024-02-25 12:15:00	2024-03-11 12:15:00	2024-03-09 10:45:00	28
9	33	2024-02-28 10:00:00	2024-03-14 10:00:00	2024-03-12 15:20:00	29
10	38	2024-03-02 15:30:00	2024-03-16 15:30:00	2024-03-14 09:30:00	30
1	13	2024-03-31 10:00:00	2024-04-14 10:00:00	\N	31
2	27	2024-03-31 11:30:00	2024-04-14 11:30:00	\N	32
3	4	2024-04-01 09:15:00	2024-04-15 09:15:00	\N	33
4	18	2024-04-01 14:45:00	2024-04-15 14:45:00	\N	34
5	19	2024-04-02 12:00:00	2024-04-16 12:00:00	\N	35
6	23	2024-04-02 16:30:00	2024-04-16 16:30:00	\N	36
7	29	2024-04-03 11:00:00	2024-04-17 11:00:00	\N	37
8	32	2024-04-03 15:15:00	2024-04-17 15:15:00	\N	38
9	34	2024-04-04 13:30:00	2024-04-18 13:30:00	\N	39
10	37	2024-04-04 10:45:00	2024-04-18 10:45:00	\N	40
1	39	2024-01-10 14:00:00	2024-01-24 14:00:00	2024-01-22 11:30:00	41
2	42	2024-01-12 09:30:00	2024-01-26 09:30:00	2024-01-24 16:15:00	42
3	43	2024-01-15 11:45:00	2024-01-29 11:45:00	2024-01-27 13:00:00	43
4	44	2024-01-18 15:30:00	2024-02-01 15:30:00	2024-01-30 10:45:00	44
5	46	2024-01-20 12:15:00	2024-02-03 12:15:00	2024-02-01 14:30:00	45
6	47	2024-01-22 10:00:00	2024-02-05 10:00:00	2024-02-03 16:00:00	46
7	48	2024-01-25 13:45:00	2024-02-08 13:45:00	2024-02-06 11:15:00	47
8	49	2024-01-28 11:30:00	2024-02-11 11:30:00	2024-02-09 15:45:00	48
9	50	2024-01-30 16:15:00	2024-02-13 16:15:00	2024-02-11 12:30:00	49
10	10	2024-02-02 14:00:00	2024-02-16 14:00:00	2024-02-14 10:00:00	50
1	5	2025-06-02 09:31:51.642013	2025-06-16 09:31:51.642013	2025-06-02 09:34:38.761401	51
1	5	2025-06-02 09:41:12.613943	2025-06-16 09:41:12.613943	\N	52
3	8	2025-06-04 11:57:21.519955	2025-06-18 11:57:21.519955	\N	54
3	8	2025-06-04 12:00:35.536288	2025-06-18 12:00:35.536288	2025-06-18 12:00:35.5303	55
3	5	2025-06-04 12:17:44.655572	2025-06-18 12:17:44.655572	2025-06-19 12:17:44.650564	56
3	5	2025-06-04 12:31:17.149192	2025-06-18 12:31:17.149192	2025-06-19 12:31:17.143193	57
1	5	2025-06-02 09:46:55.06552	2025-06-30 09:46:55.06552	2025-06-04 12:51:34.374313	53
2	6	2025-06-04 13:05:11.782756	2025-06-18 13:05:11.782756	2025-06-18 13:05:11.782756	60
2	6	2025-06-04 13:15:31.477934	2025-06-18 13:15:31.477934	\N	61
3	5	2025-06-04 13:04:04.412982	2025-06-18 13:04:04.412982	2025-06-05 09:42:44.159621	59
3	5	2025-06-04 12:49:59.195102	2025-06-18 12:49:59.195102	2025-06-05 09:43:39.545654	58
4	5	2025-06-05 11:20:06.359114	2025-06-19 11:20:06.359114	2025-06-05 11:21:03.946722	62
4	5	2025-06-05 11:22:22.650424	2025-06-19 11:22:22.650424	2025-06-05 11:23:48.829371	63
2	2	2025-06-06 21:41:22.880841	2025-06-20 21:41:22.880841	\N	64
\.


--
-- TOC entry 4927 (class 0 OID 16877)
-- Dependencies: 222
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (user_id, first_name, last_name, email, password, registration_date) FROM stdin;
1	Anna	Andersson	anna.andersson@email.com	password123	2024-01-15 10:30:00
2	Erik	Eriksson	erik.eriksson@email.com	password456	2024-01-20 14:15:00
3	Maria	Karlsson	maria.karlsson@email.com	password789	2024-02-01 09:45:00
4	Johan	Johansson	johan.johansson@email.com	secure123	2024-02-10 16:20:00
5	Emma	Svensson	emma.svensson@email.com	mypass456	2024-02-15 11:00:00
6	Oscar	Nilsson	oscar.nilsson@email.com	secret789	2024-03-01 08:30:00
7	Sofia	Larsson	sofia.larsson@email.com	pass1234	2024-03-05 13:45:00
8	Lucas	Olsson	lucas.olsson@email.com	mykey567	2024-03-10 15:30:00
9	Astrid	Persson	astrid.persson@email.com	pwd890	2024-03-15 12:15:00
10	William	Gustafsson	william.gustafsson@email.com	login123	2024-03-20 17:00:00
\.


--
-- TOC entry 4939 (class 0 OID 0)
-- Dependencies: 217
-- Name: authors_author_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.authors_author_id_seq', 1, false);


--
-- TOC entry 4940 (class 0 OID 0)
-- Dependencies: 219
-- Name: books_book_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.books_book_id_seq', 58, true);


--
-- TOC entry 4941 (class 0 OID 0)
-- Dependencies: 224
-- Name: loans_loan_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.loans_loan_id_seq', 64, true);


--
-- TOC entry 4942 (class 0 OID 0)
-- Dependencies: 221
-- Name: users_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_user_id_seq', 3, true);


--
-- TOC entry 4765 (class 2606 OID 16861)
-- Name: authors authors_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.authors
    ADD CONSTRAINT authors_pkey PRIMARY KEY (author_id);


--
-- TOC entry 4767 (class 2606 OID 16870)
-- Name: books books_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.books
    ADD CONSTRAINT books_pkey PRIMARY KEY (book_id);


--
-- TOC entry 4773 (class 2606 OID 16940)
-- Name: loans loans_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.loans
    ADD CONSTRAINT loans_pkey PRIMARY KEY (loan_id);


--
-- TOC entry 4769 (class 2606 OID 16951)
-- Name: users users_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);


--
-- TOC entry 4771 (class 2606 OID 16885)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- TOC entry 4774 (class 2606 OID 16932)
-- Name: books books_author_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.books
    ADD CONSTRAINT books_author_id_fkey FOREIGN KEY (author_id) REFERENCES public.authors(author_id) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 4775 (class 2606 OID 16914)
-- Name: loans loans_book_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.loans
    ADD CONSTRAINT loans_book_id_fkey FOREIGN KEY (book_id) REFERENCES public.books(book_id) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 4776 (class 2606 OID 16923)
-- Name: loans loans_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.loans
    ADD CONSTRAINT loans_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id) ON UPDATE CASCADE ON DELETE RESTRICT;


-- Completed on 2025-06-07 22:16:58

--
-- PostgreSQL database dump complete
--

