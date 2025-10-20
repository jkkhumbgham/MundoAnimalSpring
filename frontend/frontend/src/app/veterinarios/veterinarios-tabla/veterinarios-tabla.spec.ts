import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VeterinariosTabla } from './veterinarios-tabla';

describe('VeterinariosTabla', () => {
  let component: VeterinariosTabla;
  let fixture: ComponentFixture<VeterinariosTabla>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [VeterinariosTabla]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VeterinariosTabla);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
