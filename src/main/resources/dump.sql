--
-- PostgreSQL database dump
--

-- Dumped from database version 11.6
-- Dumped by pg_dump version 12.2 (Ubuntu 12.2-4)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

--
-- Name: liked; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.liked (
    user_id integer NOT NULL,
    user_liked_id integer NOT NULL,
    value character varying(3) NOT NULL,
    "timestamp" timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.liked OWNER TO postgres;

--
-- Name: login_time; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.login_time (
    id integer NOT NULL,
    user_id integer NOT NULL,
    "timestamp" timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.login_time OWNER TO postgres;

--
-- Name: login_time_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.login_time_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.login_time_id_seq OWNER TO postgres;

--
-- Name: login_time_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.login_time_id_seq OWNED BY public.login_time.id;


--
-- Name: messages; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.messages (
    id integer NOT NULL,
    text character varying(1000) NOT NULL,
    from_user_id integer NOT NULL,
    to_user_id integer NOT NULL,
    "timestamp" timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.messages OWNER TO postgres;

--
-- Name: messages_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.messages_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.messages_id_seq OWNER TO postgres;

--
-- Name: messages_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.messages_id_seq OWNED BY public.messages.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    job character varying(100) NOT NULL,
    username character varying(10) NOT NULL,
    password character varying(100) NOT NULL,
    picture character varying(1000),
    next integer DEFAULT 0 NOT NULL,
    "timestamp" timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: login_time id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.login_time ALTER COLUMN id SET DEFAULT nextval('public.login_time_id_seq'::regclass);


--
-- Name: messages id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.messages ALTER COLUMN id SET DEFAULT nextval('public.messages_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Data for Name: liked; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.liked (user_id, user_liked_id, value, "timestamp") FROM stdin;
11	12	yes	2020-06-12 14:37:10.75321
12	11	yes	2020-06-12 21:26:14.935131
12	13	yes	2020-06-12 21:26:28.563442
12	14	yes	2020-06-12 21:26:40.236897
\.


--
-- Data for Name: login_time; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.login_time (id, user_id, "timestamp") FROM stdin;
1	11	2020-06-12 13:56:41.532071
2	11	2020-06-12 14:02:56.954811
3	11	2020-06-12 14:06:39.474468
4	11	2020-06-12 14:21:51.747415
5	12	2020-06-12 14:22:08.811915
6	12	2020-06-12 14:24:58.221217
7	12	2020-06-12 14:27:11.238255
8	12	2020-06-12 14:28:55.169466
9	12	2020-06-12 14:30:07.463009
10	12	2020-06-12 14:31:20.88709
11	12	2020-06-12 14:33:12.422511
12	12	2020-06-12 14:34:12.915824
13	11	2020-06-12 14:36:46.801949
14	12	2020-06-12 15:05:07.867037
15	12	2020-06-12 15:10:32.084676
16	12	2020-06-12 15:15:43.784706
17	12	2020-06-12 15:21:35.228037
18	12	2020-06-12 15:31:32.519943
19	12	2020-06-12 15:36:13.915529
20	12	2020-06-12 15:51:59.315791
21	12	2020-06-12 17:03:15.269472
22	12	2020-06-12 17:04:41.819319
23	12	2020-06-12 17:07:10.378387
24	12	2020-06-12 17:08:27.997619
25	12	2020-06-12 17:10:50.230083
26	12	2020-06-12 17:15:54.86921
27	12	2020-06-12 21:25:27.638058
28	12	2020-06-23 13:45:07.096724
29	12	2020-06-23 14:08:37.635615
30	12	2020-06-23 14:23:25.906216
31	12	2020-06-23 14:27:36.446558
32	12	2020-06-23 14:34:17.160661
33	12	2020-06-23 14:38:17.926472
34	12	2020-06-23 14:51:44.678855
35	12	2020-06-23 14:54:50.97167
36	12	2020-06-23 14:58:16.805173
37	12	2020-06-23 15:02:11.262368
38	12	2020-06-23 15:02:22.082231
39	12	2020-06-23 15:24:33.555856
\.


--
-- Data for Name: messages; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.messages (id, text, from_user_id, to_user_id, "timestamp") FROM stdin;
1	ku	11	12	2020-06-12 13:17:30.843813
2	wer	12	11	2020-06-12 13:37:42.751206
3	xxxx	12	11	2020-06-23 14:55:16.086031
4	toro	12	11	2020-06-23 14:58:59.826234
5	toro	12	11	2020-06-23 14:59:06.061293
6	toro	12	11	2020-06-23 14:59:10.092931
7	toro	12	11	2020-06-23 14:59:14.052986
8	toro	12	11	2020-06-23 14:59:19.076019
9	toro	12	11	2020-06-23 14:59:22.959221
10	toro	12	11	2020-06-23 14:59:26.960144
11	toro	12	11	2020-06-23 14:59:31.492107
12	toro	12	11	2020-06-23 14:59:35.374829
13	toro	12	11	2020-06-23 14:59:39.219136
14	toro	12	11	2020-06-23 14:59:43.926512
15	toro	12	11	2020-06-23 14:59:47.933373
16	toro	12	11	2020-06-23 14:59:51.720642
17	toro	12	11	2020-06-23 14:59:57.73768
18	toro	12	11	2020-06-23 15:00:01.436023
19	toro	12	11	2020-06-23 15:00:05.99077
20	toro	12	11	2020-06-23 15:00:11.163049
21	toro	12	11	2020-06-23 15:00:15.131197
22	toro	12	11	2020-06-23 15:00:18.808202
23	toro	12	11	2020-06-23 15:00:23.830934
24	ffff	12	11	2020-06-23 15:02:47.928338
25	olya	12	13	2020-06-23 15:03:37.13348
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, name, job, username, password, picture, next, "timestamp") FROM stdin;
11	Natasha	President	nata	093d8a0793df4654fee95cc1215555b3	https://media.gettyimages.com/photos/portrait-teenager-picture-id846730696?s=2048x2048	12	2020-06-09 17:55:25.804843
12	Dasha	Director	dasha	4bea249142664d11a289ffdf30225a91	https://media.gettyimages.com/photos/beautiful-girl-dancing-to-the-music-picture-id1028497410?s=2048x2048	13	2020-06-09 17:55:25.804843
13	Olya	Manager	olga	e44d46e0bb9691cf448a9bb19391e8ab	https://media.gettyimages.com/photos/beautiful-woman-picture-id927570754?s=2048x2048	14	2020-06-09 17:55:25.804843
14	Galya	Beauty	gal	d7271b0e449d2ba150b1e9aac7f20776	https://media.gettyimages.com/photos/smiling-young-girl-picture-id904234860?s=2048x2048	15	2020-06-09 17:55:25.804843
15	Valya	Noname	val	3a6d0284e743dc4a9b86f97d6dd1a3bf	https://media.gettyimages.com/photos/smiling-female-young-college-student-of-indian-ethnicity-picture-id578811112?s=2048x2048	11	2020-06-09 17:55:25.804843
\.


--
-- Name: login_time_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.login_time_id_seq', 39, true);


--
-- Name: messages_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.messages_id_seq', 25, true);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 15, true);


--
-- Name: liked liked_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.liked
    ADD CONSTRAINT liked_pkey PRIMARY KEY (user_id, user_liked_id);


--
-- Name: login_time login_time_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.login_time
    ADD CONSTRAINT login_time_pkey PRIMARY KEY (id);


--
-- Name: messages messages_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.messages
    ADD CONSTRAINT messages_pkey PRIMARY KEY (id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: users users_username_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_username_key UNIQUE (username);


--
-- Name: liked liked_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.liked
    ADD CONSTRAINT liked_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: liked liked_user_liked_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.liked
    ADD CONSTRAINT liked_user_liked_id_fkey FOREIGN KEY (user_liked_id) REFERENCES public.users(id);


--
-- Name: login_time login_time_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.login_time
    ADD CONSTRAINT login_time_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: messages messages_from_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.messages
    ADD CONSTRAINT messages_from_user_id_fkey FOREIGN KEY (from_user_id) REFERENCES public.users(id);


--
-- Name: messages messages_to_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.messages
    ADD CONSTRAINT messages_to_user_id_fkey FOREIGN KEY (to_user_id) REFERENCES public.users(id);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM rdsadmin;
REVOKE ALL ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

