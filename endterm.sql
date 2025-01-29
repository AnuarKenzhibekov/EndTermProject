PGDMP      '                 }         	   EndTermDB    17.2    17.2 2    i           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                           false            j           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                           false            k           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                           false            l           1262    24853 	   EndTermDB    DATABASE     �   CREATE DATABASE "EndTermDB" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
    DROP DATABASE "EndTermDB";
                     postgres    false            �            1259    24921    bookings    TABLE     �   CREATE TABLE public.bookings (
    booking_id integer NOT NULL,
    user_id integer,
    showtime_id integer,
    seat_id integer,
    hall_id integer,
    status character varying(20),
    number integer
);
    DROP TABLE public.bookings;
       public         heap r       postgres    false            �            1259    24886    hall    TABLE     ]   CREATE TABLE public.hall (
    hall_id integer NOT NULL,
    status character varying(50)
);
    DROP TABLE public.hall;
       public         heap r       postgres    false            �            1259    24869    movies    TABLE     �   CREATE TABLE public.movies (
    movie_id integer NOT NULL,
    title character varying(100) NOT NULL,
    duration text NOT NULL,
    genre character varying(50),
    rating double precision,
    status character varying(50)
);
    DROP TABLE public.movies;
       public         heap r       postgres    false            �            1259    24946    payments    TABLE     �   CREATE TABLE public.payments (
    payment_id integer NOT NULL,
    booking_id integer,
    amount integer NOT NULL,
    quantity integer NOT NULL,
    payment_status character varying(20)
);
    DROP TABLE public.payments;
       public         heap r       postgres    false            �            1259    24906    seats    TABLE     �   CREATE TABLE public.seats (
    seat_id integer NOT NULL,
    showtime_id integer,
    hall_id integer,
    number integer NOT NULL,
    "row" character varying(5),
    status character varying(20)
);
    DROP TABLE public.seats;
       public         heap r       postgres    false            �            1259    24891 	   showtimes    TABLE     �   CREATE TABLE public.showtimes (
    showtime_id integer NOT NULL,
    movie_id integer,
    hall_id integer,
    showtime timestamp without time zone,
    seat_id integer
);
    DROP TABLE public.showtimes;
       public         heap r       postgres    false            �            1259    33016    theater_reviews_review_id_seq    SEQUENCE     �   CREATE SEQUENCE public.theater_reviews_review_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 4   DROP SEQUENCE public.theater_reviews_review_id_seq;
       public               postgres    false            �            1259    24874    theater_reviews    TABLE     �   CREATE TABLE public.theater_reviews (
    review_id integer DEFAULT nextval('public.theater_reviews_review_id_seq'::regclass) NOT NULL,
    user_id integer,
    grade text,
    feedback text,
    status character varying(50)
);
 #   DROP TABLE public.theater_reviews;
       public         heap r       postgres    false    227            �            1259    24854    users    TABLE     �   CREATE TABLE public.users (
    user_id integer NOT NULL,
    username character varying(50) NOT NULL,
    email character varying(100) NOT NULL
);
    DROP TABLE public.users;
       public         heap r       postgres    false            �            1259    24859    users_roles    TABLE     �   CREATE TABLE public.users_roles (
    user_id integer NOT NULL,
    client character varying(20),
    admin character varying(20)
);
    DROP TABLE public.users_roles;
       public         heap r       postgres    false            �            1259    24966    users_user_id_seq    SEQUENCE     �   ALTER TABLE public.users ALTER COLUMN user_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.users_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public               postgres    false    217            c          0    24921    bookings 
   TABLE DATA           f   COPY public.bookings (booking_id, user_id, showtime_id, seat_id, hall_id, status, number) FROM stdin;
    public               postgres    false    224   �=       `          0    24886    hall 
   TABLE DATA           /   COPY public.hall (hall_id, status) FROM stdin;
    public               postgres    false    221   �=       ^          0    24869    movies 
   TABLE DATA           R   COPY public.movies (movie_id, title, duration, genre, rating, status) FROM stdin;
    public               postgres    false    219   �=       d          0    24946    payments 
   TABLE DATA           \   COPY public.payments (payment_id, booking_id, amount, quantity, payment_status) FROM stdin;
    public               postgres    false    225   \>       b          0    24906    seats 
   TABLE DATA           U   COPY public.seats (seat_id, showtime_id, hall_id, number, "row", status) FROM stdin;
    public               postgres    false    223   y>       a          0    24891 	   showtimes 
   TABLE DATA           V   COPY public.showtimes (showtime_id, movie_id, hall_id, showtime, seat_id) FROM stdin;
    public               postgres    false    222   �>       _          0    24874    theater_reviews 
   TABLE DATA           V   COPY public.theater_reviews (review_id, user_id, grade, feedback, status) FROM stdin;
    public               postgres    false    220   �>       \          0    24854    users 
   TABLE DATA           9   COPY public.users (user_id, username, email) FROM stdin;
    public               postgres    false    217    ?       ]          0    24859    users_roles 
   TABLE DATA           =   COPY public.users_roles (user_id, client, admin) FROM stdin;
    public               postgres    false    218   O?       m           0    0    theater_reviews_review_id_seq    SEQUENCE SET     K   SELECT pg_catalog.setval('public.theater_reviews_review_id_seq', 1, true);
          public               postgres    false    227            n           0    0    users_user_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.users_user_id_seq', 2, true);
          public               postgres    false    226            �           2606    24925    bookings bookings_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.bookings
    ADD CONSTRAINT bookings_pkey PRIMARY KEY (booking_id);
 @   ALTER TABLE ONLY public.bookings DROP CONSTRAINT bookings_pkey;
       public                 postgres    false    224            �           2606    24890    hall hall_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.hall
    ADD CONSTRAINT hall_pkey PRIMARY KEY (hall_id);
 8   ALTER TABLE ONLY public.hall DROP CONSTRAINT hall_pkey;
       public                 postgres    false    221            �           2606    24873    movies movies_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.movies
    ADD CONSTRAINT movies_pkey PRIMARY KEY (movie_id);
 <   ALTER TABLE ONLY public.movies DROP CONSTRAINT movies_pkey;
       public                 postgres    false    219            �           2606    24950    payments payments_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.payments
    ADD CONSTRAINT payments_pkey PRIMARY KEY (payment_id);
 @   ALTER TABLE ONLY public.payments DROP CONSTRAINT payments_pkey;
       public                 postgres    false    225            �           2606    24910    seats seats_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.seats
    ADD CONSTRAINT seats_pkey PRIMARY KEY (seat_id);
 :   ALTER TABLE ONLY public.seats DROP CONSTRAINT seats_pkey;
       public                 postgres    false    223            �           2606    24895    showtimes showtimes_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY public.showtimes
    ADD CONSTRAINT showtimes_pkey PRIMARY KEY (showtime_id);
 B   ALTER TABLE ONLY public.showtimes DROP CONSTRAINT showtimes_pkey;
       public                 postgres    false    222            �           2606    24880 $   theater_reviews theater_reviews_pkey 
   CONSTRAINT     i   ALTER TABLE ONLY public.theater_reviews
    ADD CONSTRAINT theater_reviews_pkey PRIMARY KEY (review_id);
 N   ALTER TABLE ONLY public.theater_reviews DROP CONSTRAINT theater_reviews_pkey;
       public                 postgres    false    220            �           2606    32985    users unique_email 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT unique_email UNIQUE (email);
 <   ALTER TABLE ONLY public.users DROP CONSTRAINT unique_email;
       public                 postgres    false    217            �           2606    32987    users unique_username 
   CONSTRAINT     T   ALTER TABLE ONLY public.users
    ADD CONSTRAINT unique_username UNIQUE (username);
 ?   ALTER TABLE ONLY public.users DROP CONSTRAINT unique_username;
       public                 postgres    false    217            �           2606    24863    users_roles user_roles_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT user_roles_pkey PRIMARY KEY (user_id);
 E   ALTER TABLE ONLY public.users_roles DROP CONSTRAINT user_roles_pkey;
       public                 postgres    false    218            �           2606    24858    users users_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public                 postgres    false    217            �           2606    24941    bookings bookings_hall_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.bookings
    ADD CONSTRAINT bookings_hall_id_fkey FOREIGN KEY (hall_id) REFERENCES public.hall(hall_id);
 H   ALTER TABLE ONLY public.bookings DROP CONSTRAINT bookings_hall_id_fkey;
       public               postgres    false    4789    224    221            �           2606    24936    bookings bookings_seat_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.bookings
    ADD CONSTRAINT bookings_seat_id_fkey FOREIGN KEY (seat_id) REFERENCES public.seats(seat_id);
 H   ALTER TABLE ONLY public.bookings DROP CONSTRAINT bookings_seat_id_fkey;
       public               postgres    false    4793    224    223            �           2606    24931 "   bookings bookings_showtime_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.bookings
    ADD CONSTRAINT bookings_showtime_id_fkey FOREIGN KEY (showtime_id) REFERENCES public.showtimes(showtime_id);
 L   ALTER TABLE ONLY public.bookings DROP CONSTRAINT bookings_showtime_id_fkey;
       public               postgres    false    4791    224    222            �           2606    24926    bookings bookings_user_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.bookings
    ADD CONSTRAINT bookings_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id);
 H   ALTER TABLE ONLY public.bookings DROP CONSTRAINT bookings_user_id_fkey;
       public               postgres    false    224    217    4781            �           2606    24956    bookings fk_hall_id    FK CONSTRAINT     v   ALTER TABLE ONLY public.bookings
    ADD CONSTRAINT fk_hall_id FOREIGN KEY (hall_id) REFERENCES public.hall(hall_id);
 =   ALTER TABLE ONLY public.bookings DROP CONSTRAINT fk_hall_id;
       public               postgres    false    221    224    4789            �           2606    24961    showtimes fk_seat_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.showtimes
    ADD CONSTRAINT fk_seat_id FOREIGN KEY (seat_id) REFERENCES public.seats(seat_id) ON UPDATE CASCADE ON DELETE CASCADE;
 >   ALTER TABLE ONLY public.showtimes DROP CONSTRAINT fk_seat_id;
       public               postgres    false    222    223    4793            �           2606    24951 !   payments payments_booking_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.payments
    ADD CONSTRAINT payments_booking_id_fkey FOREIGN KEY (booking_id) REFERENCES public.bookings(booking_id);
 K   ALTER TABLE ONLY public.payments DROP CONSTRAINT payments_booking_id_fkey;
       public               postgres    false    225    4795    224            �           2606    24916    seats seats_hall_id_fkey    FK CONSTRAINT     {   ALTER TABLE ONLY public.seats
    ADD CONSTRAINT seats_hall_id_fkey FOREIGN KEY (hall_id) REFERENCES public.hall(hall_id);
 B   ALTER TABLE ONLY public.seats DROP CONSTRAINT seats_hall_id_fkey;
       public               postgres    false    223    4789    221            �           2606    24911    seats seats_showtime_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.seats
    ADD CONSTRAINT seats_showtime_id_fkey FOREIGN KEY (showtime_id) REFERENCES public.showtimes(showtime_id);
 F   ALTER TABLE ONLY public.seats DROP CONSTRAINT seats_showtime_id_fkey;
       public               postgres    false    4791    223    222            �           2606    24901     showtimes showtimes_hall_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.showtimes
    ADD CONSTRAINT showtimes_hall_id_fkey FOREIGN KEY (hall_id) REFERENCES public.hall(hall_id);
 J   ALTER TABLE ONLY public.showtimes DROP CONSTRAINT showtimes_hall_id_fkey;
       public               postgres    false    222    221    4789            �           2606    24896 !   showtimes showtimes_movie_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.showtimes
    ADD CONSTRAINT showtimes_movie_id_fkey FOREIGN KEY (movie_id) REFERENCES public.movies(movie_id);
 K   ALTER TABLE ONLY public.showtimes DROP CONSTRAINT showtimes_movie_id_fkey;
       public               postgres    false    4785    219    222            �           2606    24881 ,   theater_reviews theater_reviews_user_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.theater_reviews
    ADD CONSTRAINT theater_reviews_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id);
 V   ALTER TABLE ONLY public.theater_reviews DROP CONSTRAINT theater_reviews_user_id_fkey;
       public               postgres    false    220    217    4781            �           2606    24864 #   users_roles user_roles_user_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT user_roles_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id);
 M   ALTER TABLE ONLY public.users_roles DROP CONSTRAINT user_roles_user_id_fkey;
       public               postgres    false    217    4781    218            c      x������ � �      `      x������ � �      ^   �   x�M�M� @��p
N@h�ƲtӸw�f�QI�I���RW=�����F\ňz��l���eG�\%KϘ&5�1D�G#\_$�-R�z��a�!�t%��vs��{�XVe��=̾n]�>a�i'nJ�E�3F      d      x������ � �      b      x������ � �      a      x������ � �      _   =   x�3�4�4�3�tJ-)I-R(�H�S���K�L,K��IL�I�2�4�4�3 *).A����� <��      \   ?   x�3�t�+M,��Nͫ��LJ��/K	8��&f��%��rq�$�e&�@��Ғ�J$�=... ���      ]      x������ � �     