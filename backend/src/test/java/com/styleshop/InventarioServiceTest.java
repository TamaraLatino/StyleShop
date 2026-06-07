package com.styleshop;

import com.styleshop.dto.InventarioDTO.ActualizarStockRequest;
import com.styleshop.dto.InventarioDTO.CrearInventarioRequest;
import com.styleshop.dto.InventarioDTO.InventarioResponse;
import com.styleshop.model.Inventario;
import com.styleshop.model.Producto;
import com.styleshop.repository.InventarioRepository;
import com.styleshop.repository.ProductoRepository;
import com.styleshop.service.impl.InventarioServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias — InventarioService")
class InventarioServiceTest {

    @Mock
    private InventarioRepository inventarioRepository;

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private InventarioServiceImpl inventarioService;

    private Producto productoMock;

    @BeforeEach
    void setUp() {
        productoMock = new Producto();
        productoMock.setIdProducto(1L);
        productoMock.setNombre("Camisa Polo");
    }

    @Test
    @DisplayName("Debe activar stockBajo=true cuando la cantidad queda por debajo del mínimo")
    void debeActivarAlertaStockBajoCuandoCantidadEsMenorAlMinimo() {

        Inventario inventarioExistente = Inventario.builder()
                .idInventario(1L)
                .producto(productoMock)
                .talla("M")
                .color("Azul")
                .cantidadDisponible(10)
                .stockMinimo(5)
                .stockBajo(false)
                .build();

        ActualizarStockRequest request = ActualizarStockRequest.builder()
                .cantidadDisponible(3)
                .build();

        Inventario inventarioActualizado = Inventario.builder()
                .idInventario(1L)
                .producto(productoMock)
                .talla("M")
                .color("Azul")
                .cantidadDisponible(3)
                .stockMinimo(5)
                .stockBajo(true)
                .build();

        when(inventarioRepository.findById(1L)).thenReturn(Optional.of(inventarioExistente));
        when(inventarioRepository.save(any(Inventario.class))).thenReturn(inventarioActualizado);

        InventarioResponse response = inventarioService.actualizarStock(1L, request);

        assertNotNull(response);
        assertTrue(response.getStockBajo(),
                "stockBajo debe ser TRUE cuando la cantidad (3) es menor al mínimo (5)");
        assertEquals(3, response.getCantidadDisponible());

        verify(inventarioRepository, times(1)).findById(1L);
        verify(inventarioRepository, times(1)).save(any(Inventario.class));
    }

    @Test
    @DisplayName("Debe mantener stockBajo=false cuando la cantidad es igual o mayor al mínimo")
    void debeMantenerStockBajoFalseCuandoCantidadEsSuficiente() {

        Inventario inventarioExistente = Inventario.builder()
                .idInventario(2L)
                .producto(productoMock)
                .talla("L")
                .color("Rojo")
                .cantidadDisponible(2)
                .stockMinimo(5)
                .stockBajo(true)
                .build();

        ActualizarStockRequest request = ActualizarStockRequest.builder()
                .cantidadDisponible(10)
                .build();

        Inventario inventarioActualizado = Inventario.builder()
                .idInventario(2L)
                .producto(productoMock)
                .talla("L")
                .color("Rojo")
                .cantidadDisponible(10)
                .stockMinimo(5)
                .stockBajo(false)
                .build();

        when(inventarioRepository.findById(2L)).thenReturn(Optional.of(inventarioExistente));
        when(inventarioRepository.save(any(Inventario.class))).thenReturn(inventarioActualizado);

        InventarioResponse response = inventarioService.actualizarStock(2L, request);

        assertNotNull(response);
        assertFalse(response.getStockBajo(),
                "stockBajo debe ser FALSE cuando la cantidad (10) es mayor al mínimo (5)");
        assertEquals(10, response.getCantidadDisponible());

        verify(inventarioRepository, times(1)).save(any(Inventario.class));
    }

    @Test
    @DisplayName("Debe retornar únicamente los registros con stockBajo=true")
    void debeRetornarSoloProductosConStockBajo() {

        Inventario inv1 = Inventario.builder()
                .idInventario(1L).producto(productoMock)
                .talla("M").color("Azul").cantidadDisponible(3)
                .stockMinimo(5).stockBajo(true).build();

        Inventario inv2 = Inventario.builder()
                .idInventario(2L).producto(productoMock)
                .talla("L").color("Verde").cantidadDisponible(1)
                .stockMinimo(5).stockBajo(true).build();

        when(inventarioRepository.findByStockBajoTrue()).thenReturn(List.of(inv1, inv2));

        List<InventarioResponse> resultado = inventarioService.obtenerProductosConStockBajo();

        assertEquals(2, resultado.size(),
                "Deben retornarse exactamente 2 registros con stock bajo");
        assertTrue(resultado.stream().allMatch(InventarioResponse::getStockBajo),
                "Todos los registros retornados deben tener stockBajo=true");

        verify(inventarioRepository, times(1)).findByStockBajoTrue();
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando el ID de inventario no existe")
    void debeLanzarExcepcionCuandoInventarioNoExiste() {

        when(inventarioRepository.findById(999L)).thenReturn(Optional.empty());

        ActualizarStockRequest request = ActualizarStockRequest.builder()
                .cantidadDisponible(5).build();

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> inventarioService.actualizarStock(999L, request));

        assertTrue(exception.getMessage().contains("999"));
    }
}