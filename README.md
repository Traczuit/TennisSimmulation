## Respuestas SQL

SELECT * FROM producto WHERE id_rubro = _id de libreria_ AND fecha >= NOW() - INTERVAL '24 HOURS';

SELECT nombre, SUM(precio_unitario) AS total FROM cliente, venta WHERE cliente.id_cliente=venta.id_cliente;

SELECT nombre, nombre, SUM(cantidad) FROM cliente, producto, venta WHERE cliente.id_cliente=venta.id_cliente AND producto.codigo=venta.codigo_producto AND _este mes_
