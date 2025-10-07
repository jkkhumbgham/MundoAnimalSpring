import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsuariosFormulario } from './usuarios-formulario';

describe('UsuariosFormulario', () => {
  let component: UsuariosFormulario;
  let fixture: ComponentFixture<UsuariosFormulario>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UsuariosFormulario]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UsuariosFormulario);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
