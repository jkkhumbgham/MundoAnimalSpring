import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VeterinariosFormulario } from './veterinarios-formulario';

describe('VeterinariosFormulario', () => {
  let component: VeterinariosFormulario;
  let fixture: ComponentFixture<VeterinariosFormulario>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [VeterinariosFormulario]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VeterinariosFormulario);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
