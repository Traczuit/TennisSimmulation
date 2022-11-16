# Respuestas SQL

### Todos los productos del rubro "librería", creados hoy.

`SELECT * FROM producto WHERE id_rubro = <id de libreria> AND fecha = EXTRACT(DAY FROM CURRENT_TIMESTAMP);`

### Monto total vendido por cliente (mostrar nombre del cliente y monto).

`SELECT nombre, SUM(precio_unitario) AS total FROM cliente, venta WHERE cliente.id_cliente = venta.id_cliente;`

### Cantidad de ventas por producto.

`SELECT nombre, SUM(cantidad) FROM producto, venta WHERE producto.codigo = venta_codigo_producto;`

### Cantidad de productos comprados por cliente en el mes actual.

`SELECT nombre, nombre, SUM(cantidad) FROM cliente, producto, venta WHERE cliente.id_cliente = venta.id_cliente AND producto.codigo = venta.codigo_producto AND EXTRACT(MONTH FROM venta.fecha) = EXTRACT(MONTH FROM CURRENT_TIMESTAMP);`

### Ventas que tienen al menos un producto del rubro "bazar".

`SELECT * FROM venta WHERE EXISTS(SELECT codigo_producto FROM venta WHERE venta.codigo_producto = producto.codigo AND producto.id_rubro = <id de bazar> `

### Rubros que no tienen ventas en los últimos 2 meses.

``
