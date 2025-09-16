import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeaderVeterinario } from './header-veterinario';

describe('HeaderVeterinario', () => {
  let component: HeaderVeterinario;
  let fixture: ComponentFixture<HeaderVeterinario>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [HeaderVeterinario]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HeaderVeterinario);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
