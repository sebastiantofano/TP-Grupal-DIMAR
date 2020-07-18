use db;

SET FOREIGN_KEY_CHECKS = 0; 
TRUNCATE TABLE `establecimiento`;
TRUNCATE TABLE `zona`;
TRUNCATE TABLE `zona_provincia`;
TRUNCATE TABLE `usuario`;
TRUNCATE TABLE `insumo`;
TRUNCATE TABLE `provincia`;
TRUNCATE TABLE `responsable`;
TRUNCATE TABLE `tipoDistribucion`;
TRUNCATE TABLE `estadodistribucion`;
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO `zona` (`id`,`nombre`,`puntaje`) VALUES 
(1,"ZONA A", 100),
(2,"ZONA B", 70),
(3,"ZONA C", 50),
(4,"ZONA D", 30),
(5,"ZONA E", 10);

INSERT INTO `provincia` (`id`,`codigo`,`nombre`) VALUES 
(1,"AR-Z", "Santa Cruz"),
(2,"AR-X", "Córdoba"),
(3,"AR-Y", "Jujuy"),
(4,"AR-V", "Tierra del Fuego"),
(5,"AR-W", "Tucumán"),
(6,"AR-T", "Chubut"),
(7,"AR-U", "Río Negro"),
(8,"AR-R", "Corrientes"),
(9,"AR-S", "Santa Fe"),
(10,"AR-P", "Formosa"),
(11,"AR-Q", "Neuquén"),
(12,"AR-N", "Misiones"),
(13,"AR-L", "La Pampa"),
(14,"AR-M", "Mendoza"),
(15,"AR-J", "San Juan"),
(16,"AR-K", "Catamarca"),
(17,"AR-H", "Chaco"),
(18,"AR-F", "La Rioja"),
(19,"AR-G", "Santiago del Estero"),
(20,"AR-D", "San Luis"),
(21,"AR-E", "Entre Ríos"),
(22,"AR-B", "Buenos Aires"),
(23,"AR-C", "Ciudad de Buenos Aires"),
(24,"AR-A", "Salta");

INSERT INTO `zona_provincia` (`Zona_id`,`provincias_id`) VALUES
(1,2),(1,3),(1,1),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),
(2,11),(2,12),(2,13),(2,14),(2,15),(2,16),(2,17),
(3,18),(3,19),(3,20),(3,21),
(4,22),(4,23),(4,24);

INSERT INTO `tipodistribucion` (`id`,`nombre`) VALUES
(1,'Combinada'),
(2,'Ocupacion'),
(3,'Capacidad Total'),
(4,'Zona'),
(5,'Equitativa');

INSERT INTO `estadodistribucion` (`id`,`descripcion`) VALUES
(1,'Pendiente de Revision'),
(2,'Pendiente de Coordinacion'),
(3,'Entrega Coordinada'),
(4,'Entregado'),
(5,'Problema en Revision'),
(6,'Problema en Coordinacion'),
(7,'Problema en Entrega');

INSERT INTO `responsable` (`id`,`nombre`,`apellido`,`edad`,`titulo`,`zona_id`) VALUES 
(1,"Enric","Montoya",45,"Título de Médico Especialista",1),
(2,"Jacobo","Fuertes",35,"Gobernador de la ciudad",2),
(3,"Carlos","Alonso",60,"Gobernador de la ciudad",3),
(4,"Luis","Alonso",46,"Jefe de Hospital",1),
(5,"Gustavo","Montes",41,"Jefe de Hospital",2),
(6,"Antonio","Fuertes",37,"Título de Médico Especialista",5),
(7,"Alberto","Rivera",39,"Rector de Hospital",4),
(8,"Alberto","Ibañez",56,"Jefe de Hospital",4),
(9,"Omar","Fernandez",67,"Título de Médico Especialista",4),
(10,"Jose","Alonso",78,"Rector de Hospital",5);

INSERT INTO `establecimiento` (`id`,`capacidad`,`ocupacion`,`nombre`,`responsable_id`,`ubicacion`,`zona_id`) VALUES 
(1,1000,500,"Centro Médico ALBOR",1,"-32.6583809,-58.5238157", 1),
(2,2000,300,"Instituto VITA",2,"-39.1583809,-65.5238157",1),
(3,3000,100,"Clínica del Prado",4,"-34.6583809,-59.1238157",2),
(4,4000,200,"Hospital Provincial",5,"-32.6583809,-64.5238157",2),
(5,1000,300,"Sanatorio de los Arrayos",7,"-33.6583809,-62.5238157",3),
(6,5000,400,"Instituto Tucumano",6,"-26.6583809,-65.5238157",3),
(7,3000,200,"Centro Asistencial Gierson",3,"-33.3583809,-61.5238157",4),
(8,1000,100,"Centro de Salud Almagro",10,"-36.6583809,-58.7238157",4),
(9,2000,500,"Hospital General Alvarez",9,"-32.6583809,-66.5238157",5),
(10,4000,200,"Hospital Piñero",8,"-34.6583809,-67.5238157",5);

INSERT INTO `insumo` (`id`,`cantidad`,`nombre`,`tipo`,`precioUnidad`) VALUES 
(1,102,"Respiradores","Tipo B",3400),
(2,203,"Medicamentos","Tipo C",120),
(3,304,"Jeringas","Tipo A",110),
(4,400,"Tapa bocas","Tipo D",50),
(5,100,"Delantales","Tipo C",69),
(6,200,"Camas","Tipo F",2500),
(7,400,"Guantes","Tipo B",55);

INSERT INTO `usuario` (`id`,`email`,`password`,`rol`) VALUES
(1,'admin@admin.com','admin','admin');

