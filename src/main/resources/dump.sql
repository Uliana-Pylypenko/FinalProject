# Category
INSERT INTO categories(name) VALUE ('observatory');
INSERT INTO categories(name) VALUE ('hotel');
INSERT INTO categories(name) VALUE ('stargazing-site');

# Places
INSERT INTO places(is_approved, latitude, longitude, name, category_id, user_id) VALUES (true, 52.2170, 21.0276, 'Astronomical Observatory of the University of Warsaw', 1, 1);
INSERT INTO places(is_approved, latitude, longitude, name, category_id, user_id) VALUES (false, 52.08972, 21.42, 'Observational station in Ostrowik', 1, 1);
INSERT INTO places(is_approved, latitude, longitude, name, category_id, user_id) VALUES (false, 45.580372, 27.036896, 'House of traveller', 2, 2);
# uj for demo

# Place details
INSERT INTO place_details(activities, address, country, description, location, place_id) VALUES ('observations, stargazing, workshops', 'al. Ujazdowskie 4', 'Poland', '200-year old astronomical observatory near Lazienki park.', 'Warsaw', 1);
INSERT INTO place_details(activities, address, country, description, location, place_id) VALUES ('observations, stargazing, workshops', 'Ostrowik 21, 05-430 Celestynow', 'Poland', 'Observatory with a 0.6m telescope, Solar System exhibition, and nice forest around.', 'Ostrowik', 2);


# Events
INSERT INTO events(date, description, time, title, place_id) VALUES ('2025-04-29', 'Lets observe solar ecslipse together! We have telescopes and other stuff.' ,'15:00:00', 'Solar eclipse observations', 1);
INSERT INTO events(date, description, time, title, place_id) VALUES ('2025-05-05', 'We will observe visible planets using a telescope and binoculars, and learn more about them.', '20:00:00', 'Observing planets', 2);