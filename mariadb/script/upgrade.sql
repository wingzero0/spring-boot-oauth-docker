use oauth2;

ALTER TABLE `oauth_client_details`
ADD `display_name` varchar(256) COLLATE 'utf8_bin' NULL;