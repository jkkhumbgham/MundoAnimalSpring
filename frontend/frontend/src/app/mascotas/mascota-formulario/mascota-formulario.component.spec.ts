import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MascotaFormulario } from './mascota-formulario.component';

describe('MascotaFormulario', () => {
  let component: MascotaFormulario;
  let fixture: ComponentFixture<MascotaFormulario>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MascotaFormulario]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MascotaFormulario);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
