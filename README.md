# Simulador de Estacionamiento

## Descripción

Sistema de gestión de estacionamiento desarrollado en Java que permite administrar el ingreso y egreso de vehículos, calcular costos por tiempo de estadía y mantener un historial de operaciones.


## Características

- ✅ Registro de ingreso de vehículos
- 💸 Cálculo automático de tarifas por tiempo de estadía
- 📊 Visualización de vehículos estacionados
- 📝 Historial de tickets completados
- 🚗 Control de capacidad máxima del estacionamiento
- ⏰ Registro de hora de entrada y salida

## Estructura del Proyecto

```
com.dip.parkinglotsimulator/
├── Main.java                    # Clase principal con menú interactivo
├── models/
│   ├── ParkingLot.java         # Gestión del estacionamiento
│   ├── Ticket.java             # Manejo de tickets y cálculo de costos
│   └── Vehicle.java            # Representación de vehículos
└── utils/
    └── Constants.java          # Constantes de configuración
```

## Configuración

Las siguientes constantes pueden modificarse en `Constants.java`:

- **MAX_CAPACITY**: Capacidad máxima del estacionamiento (por defecto: 5)
- **HOURLY_RATE**: Tarifa por hora en pesos (por defecto: $2000)
- **MINUTES_PER_HOUR**: Minutos por hora para cálculos (60)

## Funcionalidades

### 1. Ingresar Vehículo
Registra un nuevo vehículo en el estacionamiento solicitando:
- Patente
- Marca
- Modelo

### 2. Retirar Vehículo
Retira un vehículo mediante su patente y calcula el costo total basado en el tiempo de estadía.

### 3. Mostrar Vehículos Estacionados
Lista todos los vehículos actualmente en el estacionamiento.

### 4. Mostrar Historial de Tickets
Muestra el historial completo de vehículos que ya se retiraron con sus costos.

### 5. Mostrar Lugares Disponibles
Indica cuántos espacios quedan disponibles en el estacionamiento.


## Cálculo de Tarifas

El sistema calcula el costo basándose en:
- Tiempo de estadía en minutos
- Redondeo hacia arriba por hora completa
- Tarifa: $2000 por hora

**Ejemplo**: 
- 30 minutos = 1 hora = $2000
- 90 minutos = 2 horas = $4000

## Uso

### Compilación
```bash
javac com/dip/parkinglotsimulator/Main.java
```
### Ejecución
```bash
java com.dip.parkinglotsimulator.Main
```

## Menú Principal

```
===== SIMULADOR DE ESTACIONAMIENTO =====
1. Ingresar vehículo
2. Retirar vehículo
3. Mostrar vehículos estacionados
4. Mostrar historial de tickets
5. Mostrar lugares disponibles
0. Salir
```


## Validaciones

- ✔️ No permite estacionar más vehículos que la capacidad máxima
- ✔️ Detecta si un vehículo ya está estacionado (por patente)
- ✔️ Verifica que el vehículo exista antes de retirarlo
- ✔️ Manejo de opciones inválidas en el menú


## Tecnologías

- Java 8+
- LocalDateTime para manejo de fechas
- Collections Framework (ArrayList)