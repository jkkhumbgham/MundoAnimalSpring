import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsuariosDetalle } from './usuarios-detalle';

describe('UsuariosDetalle', () => {
  let component: UsuariosDetalle;
  let fixture: ComponentFixture<UsuariosDetalle>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UsuariosDetalle]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UsuariosDetalle);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
