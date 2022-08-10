USE [master]
GO
CREATE DATABASE [asset_recommender]
GO
USE [asset_recommender]
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [deal]
(
  [id]         INT IDENTITY (1, 1) NOT NULL
    PRIMARY KEY,
  [start_date] DATETIME2           NOT NULL,
  [end_date]   DATETIME2           NOT NULL
);

CREATE TABLE [asset]
(
  [id]      INT IDENTITY (1, 1) NOT NULL
    PRIMARY KEY,
  [name]    VARCHAR(100)        NOT NULL,
  [cost]    DECIMAL(18, 2)      NOT NULL,
  [type]    VARCHAR(30)         NOT NULL,
  [deal_id] INT
    FOREIGN KEY REFERENCES [deal] ([id])
);

CREATE TABLE [customer]
(
  [id]            INT IDENTITY (1, 1) NOT NULL
    PRIMARY KEY,
  [name]          VARCHAR(100)        NOT NULL,
  [affordability] DECIMAL(18, 2)      NOT NULL
);

CREATE TABLE [customer_deal]
(
  [id]          INT IDENTITY (1, 1) NOT NULL
    PRIMARY KEY,
  [deal_id]     INT                 NOT NULL
    FOREIGN KEY REFERENCES [deal] ([id]),
  [customer_id] INT                 NOT NULL
    FOREIGN KEY REFERENCES [customer] ([id])
);

INSERT
INTO [deal] ([start_date], [end_date])
VALUES
  ('2022-01-01 00:00:00', '2023-01-01 00:00:00'),
  ('2019-04-15 00:00:00', '2022-03-15 00:00:00'),
  ('2016-07-29 00:00:00', '2021-07-29 00:00:00'),
  ('2022-05-17 00:00:00', '2024-08-17 00:00:00');

INSERT
INTO [asset] ([name], [cost], [type], [deal_id])
VALUES
  ('Tractor', 50000, 'AGRICULTURE', 1),
  ('Combine Harvester', 100000, 'AGRICULTURE', 1),
  ('Baler', 55000, 'AGRICULTURE', NULL),
  ('Sprinkler', 125000, 'AGRICULTURE', NULL),
  ('Taxi 1', 20000, 'TRANSPORT', 2),
  ('Taxi 2', 30000, 'TRANSPORT', 2),
  ('Bus', 250000, 'TRANSPORT', 2),
  ('Ferry', 1500000, 'TRANSPORT', 3),
  ('Garbage Truck', 170000, 'WASTE', 4),
  ('Biomass Boiler', 75000, 'WASTE', NULL);

INSERT
INTO [customer] ([name], [affordability])
VALUES
  ('John', 75000),
  ('Wendy', 300000),
  ('Bill', 1500000),
  ('Ted', 20000),
  ('Dave', 150000),
  ('Lucy', 74000),
  ('Katy', 500000);

INSERT
INTO [customer_deal] ([deal_id], [customer_id])
VALUES
  (1, 1),
  (1, 2),
  (2, 2),
  (2, 3),
  (2, 4),
  (3, 3),
  (3, 5),
  (4, 5),
  (4, 6);