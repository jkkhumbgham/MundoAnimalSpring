import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FooterTodo } from './footer-todo';

describe('FooterTodo', () => {
  let component: FooterTodo;
  let fixture: ComponentFixture<FooterTodo>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FooterTodo]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FooterTodo);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
