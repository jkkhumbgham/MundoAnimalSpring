import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsuariosTabla } from './usuarios-tabla';

describe('UsuariosTabla', () => {
  let component: UsuariosTabla;
  let fixture: ComponentFixture<UsuariosTabla>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UsuariosTabla]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UsuariosTabla);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
